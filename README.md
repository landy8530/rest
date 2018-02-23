# 代码介绍
com.jersey包下为jersey1.x 版本的demo

com.restful包下为jersey2.x 版本的demo
# jar包详解
    > jersey-client 是jersey提供的客户端包，封装了一些客户端操作的类
    > jersey-container-servlet是jersey的核心，服务端必备包
    > jersey-media-moxy 是定义了jersry支持的常用的数据格式，json，xml都包括其中
    > jersey-media-multipart 是jersey的上传文件的支持

# Jersey提供3种基本方式来使用JSON格式:
## 1.基于POJO
Request类和Response类（服务端和客户端都需要）都是基本的POJO

## 2.基于JAXB
使用JAXB的优点在于，无论使用XML格式还是JSON格式数据，都可以使用统一的JAVA模型
缺点很难找到一个合适的方式来生成特殊的JSON格式，这也是Jersey提供很多控制选项的原因

## 3.基于底层JSONObject/JSONArray
最大的优势在于可以完全控制JSON的解析、组装过程，相应的，在处理数据对象时也要更复杂
与JAXB相比，结果是相同的，但是处理过程（主要是组装JSON对象）要复杂

对于上面3种方式，均可使用String类代替Request类、Response类或JSONObject类，Jersey会自动将对象转换为JSON串

# Jersey对HTTPS的支持

证书的生成过程可以参考[Java Secure(SSL/TLS) Socket实现](https://github.com/landy8530/socket)

# jersey中有几种常用的接收参数的注解

 @PathParam 接收链接中参数，如”/xxx/{name}/”,@PathParm(“name”)
 @QueryParam 接收链接中的普通参数，如”/xxx?name=ttt”,@QueryParam(“name”)
 @FormParm 接收post提交中的表单参数
 @FormDataParm 上传文件接收文件参数

# json
 开发中，json已经常用到无处不在了，jersey对json的支持很好。

 接收json，需要使用@Consumes，注解指定解压方式：
 @Consumes(MediaType.APPLICATION_JSON)

 返回json需要使用@Produces注解，指定压缩方式：
 @Produces(MediaType.APPLICATION_JSON)

# 文件上传

```
@POST
  @Path("import-excel")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.APPLICATION_JSON)
  public ImportResultBean importForExcel(@FormDataParam("file") String fileString,
                                         @FormDataParam("file") InputStream fis,
                                         @FormDataParam("file") FormDataContentDisposition fileDisposition) {
    // TODO
    return ;
  }
```

# 文件下载

文件下载需要将Response对象的压缩方式，指定为

```
@Produces(MediaType.APPLICATION_OCTET_STREAM)
```