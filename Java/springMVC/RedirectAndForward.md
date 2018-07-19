1. 重定向   
浏览器->服务器servlet1->浏览器->服务器servlet2->浏览器   
```java
private void response401(ServletRequest req, ServletResponse resp, String msg) {
    try {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        //通过url传参
        httpServletResponse.sendRedirect("/selfmes/401?msg="+msg);
    } catch (IOException e) {
        LOGGER.error(e.getMessage());
    }

}
```

1. 转发
浏览器->服务器servlet1->服务器servlet2->浏览器   
```java
private void response401(ServletRequest req, ServletResponse resp, String msg) {
    try {
        //通过request传参
        req.setAttribute("msg",msg);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/401");
        requestDispatcher.forward(req, resp);

    } catch (IOException e) {
        LOGGER.error(e.getMessage());
    }catch (ServletException e) {
        LOGGER.error(e.getMessage());
    }
}
```

https://www.cnblogs.com/flyingeagle/articles/6681270.html
