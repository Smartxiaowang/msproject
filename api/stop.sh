export JAR_HOME="/root/project/msproject/api/bs-wx/target"
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
exit
