package org.hospital.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Servlet Filter implementation class UtfFilter
 *
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 17:15
 * @Description 字符编码过滤器
 */
public class CharacterEncodingFilter implements Filter {
    /**
     * 定义一个获取初始化值的标杆
     */
    private static String encoding = null;

    /**
     * Default constructor.
     */
    public CharacterEncodingFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (encoding != null) {
            // 设置请求的编码格式
            request.setCharacterEncoding(encoding);
            // 响应的编码格式
            response.setCharacterEncoding(encoding);
            // 放行
            chain.doFilter(request, response);
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // 获取初始化变量赋值
        encoding = fConfig.getInitParameter("character");
    }

}
