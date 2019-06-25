package cn.javabs.demo.servlet;

import cn.javabs.demo.entity.Source;
import cn.javabs.demo.service.SourceService;
import cn.javabs.demo.service.impl.SourceServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 资源管理
 *  |-- 上传
 *  |-- 下载
 */
@WebServlet("/sourceServlet")
@MultipartConfig(maxFileSize = 1024*50*1024)
public class SourceServlet extends HttpServlet {

    Source source = new Source();
    SourceService sourceService = new SourceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet( request,  response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");

        String op = request.getParameter("op");
        switch (op){
            case "uploadSource":
                uploadSource(request,response);
                break;
            case "downloadSource":
                downloadSource(request,response);
                break;
            case "delSource":
                delSource(request,response);
                break;
            case "findAllSources":
                findAllSources(request,response);
                break;
            default:
                System.out.println("没有找到指定参数");
        }


    }

    /**
     * 删除资源
     * @param request
     * @param response
     */
    private void delSource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int sourceId = Integer.parseInt(id);
        int resultRows  =  sourceService.delSource(sourceId);
        if (resultRows > 0){
            request.setAttribute("msg","资源删除成功！");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","删除资源失败，请核查相关信息！");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }
    }

    /**
     * 下载资源
     * @param request
     * @param response
     */
    private void downloadSource(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        int sourceId = Integer.parseInt(id);
        source  =  sourceService.findSourceById(sourceId);
        // 如果有资源，就可以进行下载
        if (source != null){
            // 获取资源文件的路径和名称
            String sourcePath = source.getSourcePath();
            String sourceName = source.getSourceName();

            String fileName = sourcePath + sourceName;

            System.out.println("fileName:" + fileName);

            File file = new File(fileName);
            if (file.exists()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    String filename = URLEncoder.encode(file.getName(), "utf-8");
                    byte[] b = new byte[fileInputStream.available()];
                    fileInputStream.read(b);
                    response.setCharacterEncoding("utf-8");
                    response.setHeader("Content-Disposition","attachment; filename="+filename+"");
                    //获取响应报文输出流对象
                    ServletOutputStream out =response.getOutputStream();
                    //输出
                    out.write(b);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    throw  new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 查询所有资源
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void findAllSources(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Source> sources = null;
        sources = sourceService.findAllSources();
        System.out.println("sources:"+sources);

        Iterator<Source> it = sources.iterator();
        while (it.hasNext()){
            source = it.next();

            if (source.getCreateTime().contains(".")){
                int index = source.getCreateTime().indexOf(".");
                String newTime = source.getCreateTime().substring(0, index);
                source.setCreateTime(newTime);
                // sources.add(source);
            }
        }
        System.out.println(sources.size());
        if (sources.size()>0 && sources != null){
            request.setAttribute("sources",sources);
            request.getRequestDispatcher("/sourceList.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","新闻查询失败！");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }

    }

    /**
     * 上传资源
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void uploadSource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 定义路径
        String realPath = this.getServletContext().getRealPath("/upload/");

        // 2. 如果该文件夹不存在、则帮我创建出来
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        Part sourceName = null;

        try {
            // 3. 获取上传文件
            sourceName = request.getPart("sourceName");
        } catch (Exception e) {
            throw new RuntimeException("\"文件上传失败，请上传小于5kb的文件\""+e);
        }


        // 4. 获取上传文件的文件名
        String fileName = sourceName.getSubmittedFileName();

        //4.2 更改上传文件名称:
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // 如果文件名称存在
        if (!fileName.equals("") || fileName != null){
            fileName = currentDate + "_" + fileName;
        }

        sourceName.write(realPath+"/"+fileName);

        source.setSourcePath(realPath);
        source.setSourceName(fileName);

        try {
            BeanUtils.populate(source,request.getParameterMap());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("[Source:]" + source);

        int resultRows = sourceService.addSource(source);

        System.out.println("4545");

        if (resultRows > 0){
            request.setAttribute("msg","添加资源成功！");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }else{
            request.setAttribute("msg","资源添加失败，请核查相关信息！");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
        }


    }
}
