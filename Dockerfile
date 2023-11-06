FROM zl52074/openjdk8:1.0
# 指定镜像维护人员
MAINTAINER zl52074
# 复制文件到目录并重命名
ADD app.jar /opt/app.jar
# 指定容器启动时要执行的命令-启动java程序
CMD java -jar /opt/app.jar
#声明端口
EXPOSE 9090
