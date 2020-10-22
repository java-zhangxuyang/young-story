package com.young.base.utils;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class PublicUtils {

    /**
     * @param request 请求
     * @return IP Address
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 生成随机图片名
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);
        
        return str;
    }
    
    /**
     * 分页参数校验修正
 *
 * @param response
 * @param uid
 */
public static Integer page(Integer pageNum, Boolean page3) {
	if(pageNum == null || pageNum < 1) {
		return 1;
	} else {
		if(page3) {
			if(pageNum > 3) {
				return 3;
			}else {
				return pageNum;
			}
		}else {
			return pageNum;
		}
	}
}
}
