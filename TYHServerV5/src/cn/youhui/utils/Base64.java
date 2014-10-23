package cn.youhui.utils;

import java.io.UnsupportedEncodingException;

public class Base64 {
    private static char[] base64EncodeChars = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};

    private static byte[] base64DecodeChars = new byte[]{
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
            -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};
    
    /**base64编码*/
    public static String encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }
    /**base64解码*/
    public static byte[] decode(String str) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        byte[] data = str.getBytes("US-ASCII");
        int len = data.length;
        int i = 0;
        int b1, b2, b3, b4;
        while (i < len) {
            do {
                b1 = base64DecodeChars[data[i++]];
            } while (i < len && b1 == -1);
            if (b1 == -1) break;
            do {
                b2 = base64DecodeChars
                        [data[i++]];
            } while (i < len && b2 == -1);
            if (b2 == -1) break;
            sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));          
            do {
                b3 = data[i++];
                if (b3 == 61) return sb.toString().getBytes("iso8859-1");
                b3 = base64DecodeChars[b3];
            } while (i < len && b3 == -1);
            if (b3 == -1) break;
            sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));           
            do {
                b4 = data[i++];
                if (b4 == 61) return sb.toString().getBytes("iso8859-1");
                b4 = base64DecodeChars[b4];
            } while (i < len && b4 == -1);
            if (b4 == -1) break;
            sb.append((char) (((b3 & 0x03) << 6) | b4));
        }
        return sb.toString().getBytes("iso8859-1");
    }
    
    
    public static void main(String[] args) throws Exception {
//    	String st = "{\"isshare\":true,\"title\":\"分享软件，送集分宝\",\"content\":\"我正在用#随手优惠#淘宝贝，购物还能赚集分宝返利，首次登陆免费领50个集分宝，每日签到能领更多，帮你淘宝省钱无下限，下载链接：http://youhui.cn/ ——来自史上最高返利的手机应用\",\"clickurl\":\"http://youhui.cn\",\"imgurl\":\"http://bcs.duapp.com/taoyouhui-ad/adImg/8b5s9q765y.jpg\",\"activity_key\":\"e1bvtgcb\",\"jifen_num\":\"5\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
        
    	String s = "{\"isshare\":true,\"title\":\"七夕秀甜蜜——你敢晒，我就敢免！\",\"content\":\"参与随手优惠新浪微活动“七夕秀甜蜜”，就有机会赢得购物车满车免单大奖，激情转起来，火速来赢七夕免单大奖吧！\",\"clickurl\":\"http://d.b17.cn/sactivity/weibo_free/\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/24tq8eurwwk.png\",\"activity_key\":\"4ueclaja\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email\"}";
//    	s = NetManager.getInstance().getContent("http://hws.alicdn.com/cache/mtop.wdetail.getItemDescx/4.1/?data={%22item_num_id%22%3A%2235846963894%22}");
//        String ds = "eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6IjLmnIjliIbnuqLluJDljZXnlJ%2FmiJDllr3vvIHvvIEiLCJjb250ZW50Ijoi6ZqP5omL5ZCb5Li65oKo6YCB5LiK54Ot6IW%2B6IW%2B55qEMuaciOWIhue6ouW4kOWNlSzlsIbkuo4xNuaXpeWIsOW4kCHnrYnnuqfotorpq5jliIbnuqLotorlpJrvvIHngrnmiJHnnIHpkrHigJTigJTmt5jlrp3otK3nianlsLHnlKjjgJDpmo/miYvkvJjmg6DjgJEiLCJjbGlja3VybCI6Imh0dHA6Ly9kLmIxNy5jbi9zYWN0aXZpdHkvZmgvZjQwMi5qc3A%2FdT1NakV4TnpJMU5EVSIsImltZ3VybCI6Imh0dHA6Ly9iY3MuZHVhcHAuY29tL3Rhb3lvdWh1aS1hZC9hZEltZy80enB4bWg0dXVhYS5qcGciLCJhY3Rpdml0eV9rZXkiOiIxZGE3Z2t0bSIsImNoYW5uZWwiOiJ3ZWl4aW4sd2VpYm8scGVuZ3lvdSx0eHdiLHFxa2oscmVucmVuLGR1b2JhbixlbWFpbCxzbSJ9";
//        System.out.println(new String(decode(ds), "UTF-8"));
        System.out.println(encode(s.getBytes()));
//    	System.out.println(new String(decode("eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6IuWIhuS6q+i9r+S7tu+8jOmAgembhuWIhuWunSIsImNvbnRlbnQiOiLmiJHmraPlnKjnlKgj6ZqP5omL5LyY5oOgI+a3mOWunei0ne+8jOi0reeJqei/mOiDvei1mumbhuWIhuWunei/lOWIqe+8jOmmluasoeeZu+mZhuWFjei0uemihjUw5Liq6ZuG5YiG5a6d77yM5q+P5pel562+5Yiw6IO96aKG5pu05aSa77yM5biu5L2g5reY5a6d55yB6ZKx5peg5LiL6ZmQ77yM5LiL6L296ZO+5o6l77yaaHR0cDovL3lvdWh1aS5jbi9oTVRZMU5EVXhNakUwIOKAlOKAlOadpeiHquWPsuS4iuacgOmrmOi/lOWIqeeahOaJi+acuuW6lOeUqCAiLCJjbGlja3VybCI6Imh0dHA6Ly95b3VodWkuY24vaE1UWTFORFV4TWpFMCIsImltZ3VybCI6Imh0dHA6Ly9iY3MuZHVhcHAuY29tL3Rhb3lvdWh1aS1hZC9hZC8xLmpwZyIsImFjdGl2aXR5X2tleSI6IjQzMDJ0bGpiIn0=")));
//		System.out.println(new String(decode("eyJpc3NoYXJlIjp0cnVlLCJ0aXRsZSI6IuWIhuS6q+a1i+ivlea0u+WKqCIsImNvbnRlbnQiOiLliIbkuqvpgIHpm4bliIblrp3llaYgaHR0cDovL3lvdWh1aS5jbiIsImNsaWNrdXJsIjoiaHR0cDovL3lvdWh1aS5jbiIsImltZ3VybCI6Imh0dHA6Ly9iY3MuZHVhcHAuY29tL3Rhb3lvdWh1aS1hZC9hZEltZy84YjVzOXE3NjV5LmpwZyIsImFjdGl2aXR5X2tleSI6ImUxYnZ0Z2NiIiwiamlmZW5fbnVtIjoiNSIsImNoYW5uZWwiOiJ3ZWl4aW4scGVuZ3lvdSx3ZWlibyx0eHdiLHFxa2oscmVucmVuLGR1b2JhbixlbWFpbCxzbSJ9")));
//        System.out.println(15*24*60*60*1000);
        
//        String ss = "eyJhcHBjb25maWciOnsiYXBwbW9uZXl0eXBlIjoiMCIsImhlYWRfYmxhY2tsaXN0IjpbXSwiYXBwY2FsYmFjayI6InN1aXNob3V5b3VodWk6Ly9hdXRocmVzdWx0IiwiYW5ub3VuY2VtZW50IjpbeyJkZWxheVRpbWUiOiIxMDAwMCIsImNvbnRlbnRVcmwiOiI8aHRtbD48Ym9keT48cD4/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/PFwvcD48cD4/Pz8/Pz8/Pz8/PyAgID8/Pz8/ICAgICAgPz8/Pz8/ICA/Pz88XC9wPjxwPj8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8/Pz88XC9wPjxcL2JvZHk+PFwvaHRtbD4iLCJsZWZ0QnV0dG9uTmFtZSI6Ij8/Pz8iLCJsZWZ0QnV0dG9uQWN0aW9uIjoiIiwibGVmdF9kaXNtaXNzX3R5cGUiOjAsImVuZFRpbWUiOiIxNDAzNzYxNjAwMDAwIiwicmlnaHRCdXR0b25OYW1lIjoiPz8/PyIsInN0YXJ0VGltZSI6IjE0MDIyNDMyMDAwMDAiLCJ0aXRsZSI6Ij8/Pz8xIiwicmlnaHRfZGlzbWlzc190eXBlIjoxLCJpdGVtX2lkIjoxMjMsInJpZ2h0QnV0dG9uQWN0aW9uIjoic3Vpc2hvdTovL2FwcC55b3VodWkuY24vVGFnU3R5bGVJdGVtUGFnZT9pdGVtX2lkPTM2MDY4NjY0NDEzJmlzX2Zhdj10cnVlJnRpdGxlPT8/Pz8/Pz8iLCJpc0NhbkRpc21pc3MiOjF9LHsiZGVsYXlUaW1lIjoiMjAwMDAiLCJjb250ZW50VXJsIjoiaHR0cDovL3dhcC5iYWlkdS5jb20iLCJsZWZ0QnV0dG9uTmFtZSI6Ij8/IiwibGVmdEJ1dHRvbkFjdGlvbiI6IiIsImxlZnRfZGlzbWlzc190eXBlIjoxLCJlbmRUaW1lIjoiMTQwMzc2MTYwMDAwMCIsInJpZ2h0QnV0dG9uTmFtZSI6IiIsInN0YXJ0VGltZSI6IjEzOTk4MjQwMDAwMDAiLCJ0aXRsZSI6Ij8/Pz8yIiwicmlnaHRfZGlzbWlzc190eXBlIjowLCJpdGVtX2lkIjo0NTYsInJpZ2h0QnV0dG9uQWN0aW9uIjoic3Vpc2hvdTovL2FwcC55b3VodWkuY24vVGFnU3R5bGVJdGVtUGFnZT9pdGVtX2lkPTM2MDY4NjY0NDEzJmlzX2Zhdj10cnVlJnRpdGxlPT8/Pz8/Pz8iLCJpc0NhbkRpc21pc3MiOjF9XSwiYXBwZmFubGlyYXRlIjoiRyIsIndlYl90aXBzX3Nob3dfdGltZSI6MCwiYXBwa2V5IjoiMTI0MTUyODkiLCJhcHBzZWNyZXQiOiI4YTM4MWZjYWJjMWI3NDAwM2M2ODk1NTBhOTVhMGYwYSIsImxvYWRpbmdfYWQiOlt7InN0YXJ0VGltZSI6MTQwNDE0NDAwMDAwMCwiaW1nX3VybCI6Imh0dHA6Ly9pbWFnZS56Y29vbC5jb20uY24vMjAxMy8yMy8yNC9tXzEzNzIyMzk4MzQ2NDEuanBnIiwiZHVyYXRpb24iOjEwMjAsIml0ZW1faWQiOiIxNSIsImVuZFRpbWUiOjE0MDU0NDAwMDAwMDAsImFjdGlvbl91cmwiOiJzdWlzaG91Oi8vYXBwLnlvdWh1aS5jbi9UYWdTdHlsZUdyaWRQYWdlP3RhZ19pZD1kZWZhdWx0JnRhZ190aXRsZT0ifV0sIndlYl90aXBzX2NsaWNrX3VybCI6Imh0dHA6Ly9zdWlzaG91LmNuIiwibG9naW5fcGFnZV9pbWdfdXJsIjoiaHR0cDovL2Jjcy5kdWFwcC5jb20vdGVzdC1hcGkvYmdfbG9naW5fdG9wLmpwZyIsImNvbGxlY3R2aWV3X3VybCI6Imh0dHA6Ly9iY3MuZHVhcHAuY29tL3Rhb3lvdWh1aWZpbGVzL0NvbGxlY3RWaWV3LmFwayIsImlzbG9jYWxzZWFyY2giOiIxIiwiY29sbGVjdHZpZXdfdmVyIjoiLTEiLCJqZmJfcmF0ZSI6IjIiLCJ3ZWJfdGlwc193aHJhdGlvIjoiNSIsImxvZ2luX3BhZ2VfYnV5X2ltZ191cmwiOiJodHRwOi8vYmNzLmR1YXBwLmNvbS90ZXN0LWFwaS9pdl9zdGFydF9sb2FkaW5nLmpwZyIsImFubm91bmNlbWVudF9mb3Jfc3BlY2lhbHVybCI6W3siY29udGVudCI6Ij8/Pz8xIGlkMTIzIiwicmVndWxhciI6Imh0dHA6Ly9iMTcqIiwiaXRlbV9pZCI6MTIzLCJjbG9zZV9kaXNtaXNzX3R5cGUiOjEsImFjdGlvbl9kaXNtaXNzX3R5cGUiOjAsImFjdGlvbl91cmwiOiJzdWlzaG91Oi8vYXBwLnlvdWh1aS5jbi9UaWFuVGlhblRlSmlhUGFnZSJ9LHsiY29udGVudCI6Ij8/Pz8yIGlkNDU2IiwicmVndWxhciI6Imh0dHA6Ly8xMC4wLioiLCJpdGVtX2lkIjo0NTYsImNsb3NlX2Rpc21pc3NfdHlwZSI6MCwiYWN0aW9uX2Rpc21pc3NfdHlwZSI6MSwiYWN0aW9uX3VybCI6InN1aXNob3U6Ly9hcHAueW91aHVpLmNuL1dvRGVUYW9CYW9QYWdlIn0seyJjb250ZW50IjoiPz8/PzMgaWQ3ODkiLCJyZWd1bGFyIjoiaHR0cDovL2g1Lm0udGFvYmFvLmNvbS9hd3AvbXRiL29saXN0Lmh0bT8jIS9hd3AvbXRiL29saXN0Lmh0bT9zdGE9NSIsIml0ZW1faWQiOjc4OSwiY2xvc2VfZGlzbWlzc190eXBlIjowLCJhY3Rpb25fZGlzbWlzc190eXBlIjowLCJhY3Rpb25fdXJsIjoic3Vpc2hvdTovL2FwcC55b3VodWkuY24vV2ViU3R5bGVQYWdlP3VybD1odHRwOi8vd2FwLmJhaWR1LmNvbSZpc19hZGRfdXNlcmluZm89ZmFsc2UifV0sInNwZWNpYWxfYmxhY2tsaXN0IjpbInN1aXNob3UiLCJpdGFvYmFvIl0sIndlYl90aXBzIjoiaHR0cDovL2Jjcy5kdWFwcC5jb20vdGVzdC1hcGkvYmdfbG9naW5fdG9wLmpwZyIsInNob3d0dGlkIjoiMCIsImRvd25fY2xpY2siOiI/Pz8/Pz8/Pz8/Pz8/Pz8/Pz8iLCJpc2ZhbmpmYiI6IjEifSwibWVudXZlcnNpb24iOiJudWxsIn0=";
//        System.out.println(new String(decode(ss)));
        
        
    }
}
