package cn.rongcoud.rce.demo.codec;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @use
 * @project netty-demo
 * @author Created by CZN on 2017/8/2.
 */
public class TestSubscribeReqProto {
    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(1);
        builder.setUserName("CZN");
        builder.setProductName("Netty Book");
        builder.setAddress("HangZhou");

        // List<String> address = new ArrayList<String>();
        // address.add("NanJing");
        // address.add("Beijing");
        // address.add("Hangzhou");
        return builder.build();
    }

    public static void main(String[] args) {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println("before code: " + req.toString());

        SubscribeReqProto.SubscribeReq req2 = createSubscribeReq();
        System.out.println("after code: " + req2.toString());

        System.out.println("Assert equal: -->" + req2.equals(req));
    }
}
