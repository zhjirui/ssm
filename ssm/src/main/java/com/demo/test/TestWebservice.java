package com.demo.test;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * Created by zhaojr on 2019/3/12.
 * webservice 客户端
 */
public class TestWebservice {

    public static void main(String[] args) {
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Client client = dcf.createClient("http://127.0.0.1:8080/service/update?wsdl");
            Object[] res = client.invoke("getUrl");
            System.out.print(res[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
