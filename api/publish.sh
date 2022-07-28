export JAR_HOME="/root/project/msproject/api/bs-wx/target"
git pull origin master

pid=`ps -ef|grep bs-wx.jar|grep -v grep|awk '{print $2}' `
is_exist(){
if [ -z "${pid}" ]; then
return 1
else
return 0
fi
}
is_exist
if [ $? -eq "0" ]; then
kill -9 $pid
echo "api is shotdown"
else
echo "api is not running" 
fi

rm -rf $JAR_HOME
echo"删除原有的jar包"
mvn clean install -Dmaven.test.skip=true -P test
echo"编译成功"
cd $JAR_HOME
nohup java -jar bs-wx.jar >console.log 2>&1 &
echo "程序已启动..."
sleep 3;
is_exist
if [ $? -eq "0" ]; then
echo "api is running. pid is ${pid} "
else
echo "api is not running."
fi
exit
