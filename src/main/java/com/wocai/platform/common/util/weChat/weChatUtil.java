package com.wocai.platform.common.util.weChat;

public class weChatUtil {
    //    public static void main(String[] args) {
//        System.out.println(new wxServiceImpl().getAccess_token());
//
//        wxServiceImpl wxService = new wxServiceImpl();
//        String values[] = {"zzh7878","2023年5月8日 15:01","ceshi"};
//        wxService.pushOneUser(wxService.getAccess_token(),
//                "oM47R5KO0kFhsScuitgSJSjiN0s",
//                "3",
//                "QhJq4RvU7qGyf7FY_ZFc1sHJCTD8VkFvtHEx8uHwY4"
//                , values);
//    }
    public static void main(String[] args) {
        wxServiceImpl wxService = new wxServiceImpl();
        String access_token = wxService.getAccess_token();
//        String openid = "oAO_a4u_NU9UyFvUixax2Gc5mrNo";
        String openid = "oAO_a4sBTbACvtyHTxtGIW2oUGzQ";
        String type = "2";
//        String templateid = "JBJ3DSkxabYCw1idcDsIOx4vPlsgK4PoMmr80Pljt6w";
        String templateid = "mQSwFneXjhqXV2EGvFpdhp_ghCbPhGFultyMmwz3btg";
        String[] keywords = new String[5];

        /**微信文档中要求的格式 "data":
         *                              { "name01":{"value": "某某"},
         *                              "thing01": {"value": "广州至北京"},
         *                              "date01": {"value": "2018-01-01"}}
         */
        keywords[0] = "2018-01-01";
        keywords[1] = "你好";
        keywords[2] = "你好4";
        keywords[3] = "123";
        keywords[4] = "124124";

        wxService.pushOneUser(access_token,openid,type,templateid,keywords);
    }
}
