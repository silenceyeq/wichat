set current_path="%cd%"
cd %current_path%
java -jar mybatis-generator-core-1.3.2.jar -configfile generatorConfig.xml -overwrite
echo &pause