export DIST_HOME="/root/project/msproject/ui/dist"
export NODE_HOME="/root/project/msproject/ui/node_modules"
/usr/local/webserver/nginx/sbin/nginx -s stop              # 停止 Nginx
echo"停止 Nginx"
rm -rf $DIST_HOME
rm -rf $NODE_HOME
echo"删除文件"
git pull

cnpm install
echo"编译成功"
npm run build
echo"打包"
chmod 777 $DIST_HOME
echo"赋权限"
/usr/local/webserver/nginx/sbin/nginx
echo"nginx is running"
