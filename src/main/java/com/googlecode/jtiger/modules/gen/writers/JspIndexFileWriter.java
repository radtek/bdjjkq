package com.googlecode.jtiger.modules.gen.writers;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.jtiger.modules.gen.FileContentGen;
import com.googlecode.jtiger.modules.gen.GenHelper;
import com.googlecode.jtiger.modules.gen.GeneratedFileWriter;

@Component
public class JspIndexFileWriter implements GeneratedFileWriter {
  private static Logger logger = LoggerFactory.getLogger(JspIndexFileWriter.class);
  @Autowired
  protected FileContentGen gen;

  @Override
  public void write(String table, String packageName) {
    String cont = gen.gen(table, packageName, "jsp_index.ftl");

    String path = new StringBuffer(1000).append(        
        GenHelper.getWebSource().getPath()).append(File.separator).append(
        GenHelper.table2Model(table)).append(File.separator)
        .append("index.jsp").toString();

    GenHelper.write(path, cont);
    logger.info("index jsp for table '{}' generated.", table);
  }

}
