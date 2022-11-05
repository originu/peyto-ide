package peyto.ide.service;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Service
public class FreemarkerTemplateService implements TemplateService {

    private Configuration cfg;
//    private StringTemplateLoader stringLoader;

    @PostConstruct
    public void startup() throws IOException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
		cfg.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "/templates");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		cfg.setWrapUncheckedExceptions(true);
		cfg.setFallbackOnNullLoopVariable(false);

		
//		## How to use String Template instead file template ## 
//		https://freemarker.apache.org/docs/api/freemarker/cache/StringTemplateLoader.html
//	   StringTemplateLoader stringLoader = new StringTemplateLoader();
//	   stringLoader.putTemplate("greetTemplate", "<#macro greet>Hello</#macro>");
//	   stringLoader.putTemplate("myTemplate", "<#include \"greetTemplate\"><@greet/> World!");
//	   this.stringLoader = stringLoader;
//	   
//	   cfg.setTemplateLoader(stringLoader);
       
	   this.cfg = cfg;
    }

    public void generate(String template, Object dataModel, Writer out) throws IOException, TemplateException {
        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate(template);
//        Template temp = cfg.getTemplate("test.ftlh");
        /* Merge data-model with template */
        temp.process(dataModel, out);
    }
}