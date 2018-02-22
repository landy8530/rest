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