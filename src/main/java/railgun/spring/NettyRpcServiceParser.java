package railgun.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by GongHuaigu on 2018/5/20.
 */
public class NettyRpcServiceParser implements BeanDefinitionParser{

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        String interfaceName = element.getAttribute("interfaceName");
        String ref = element.getAttribute("ref");
        String filter = element.getAttribute("filter");

        RootBeanDefinition beanDefinition = new RootBeanDefinition();

        beanDefinition.setBeanClass(NettyRpcService.class);
        beanDefinition.setLazyInit(false);
        beanDefinition.getPropertyValues().addPropertyValue("interfaceName",interfaceName);
        beanDefinition.getPropertyValues().addPropertyValue("ref", ref);
        beanDefinition.getPropertyValues().addPropertyValue("filter", filter);
        parserContext.getRegistry().registerBeanDefinition(interfaceName,beanDefinition);
        return beanDefinition;
    }
}
