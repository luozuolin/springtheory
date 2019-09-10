package cap2.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class CustomTypeFilter implements TypeFilter {
    /***
     *
     * @param metadataReader :读取到当前正在扫描到类的信息
     * @param metadataReaderFactory ：可以获取其他类的信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader,
                         MetadataReaderFactory metadataReaderFactory) throws IOException {
       AnnotationMetadata annotationMetadata= metadataReader.getAnnotationMetadata();
        ClassMetadata classMetadata=metadataReader.getClassMetadata();
        Resource resource=metadataReader.getResource();
        String  className=classMetadata.getClassName();
        System.out.println("---->"+className);
        if(className.contains("Controller")){
            return   true;
        }
        return false;
    }
}
