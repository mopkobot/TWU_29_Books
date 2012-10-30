#! /bin/bash

cd /opt/

#Install JDK 1.7

wget --no-cookies --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2Ftechnetwork%2Fjava%2Fjavase%2Fdownloads%2Fjdk-7u3-download-1501626.html;" http://download.oracle.com/otn-pub/java/jdk/7u3-b04/jdk-7u3-linux-x64.rpm

mv jdk-7u3-linux-x64.rpm?AuthParam=* jdk-7u3-linux-x64.rpm

rpm -ivh jdk-7u3-linux-x64.rpm

#Install Jetty
wget http://download.eclipse.org/jetty/stable-8/dist/jetty-distribution-8.1.7.v20120910.tar.gz

tar zxf jetty-distribution-8.1.7.v20120910.tar.gz

mv jetty-distribution-8.1.7.v20120910 jetty

adduser -r -m jetty

chown -R jetty:jetty /opt/jetty

cd /etc/init.d

ln -s /opt/jetty/bin/jetty.sh jetty

chkconfig --add jetty

chkconfig --level 345 jetty on

export JAVA_HOME=/usr/java/default

echo "JAVA=$JAVA_HOME/bin/java" >> /etc/default/jetty

echo 'JAVA_OPTIONS=" -server -Xms256m -Xmx1024m -XX:+DisableExplicitGC "'>> /etc/default/jetty

echo "JETTY_HOME=/opt/jetty" >> /etc/default/jetty

echo "JETTY_USER=jetty" >> /etc/default/jetty

echo "JETTY_PORT=8080 #<-- port number change if you need to" >> /etc/default/jetty

echo "JETTY_HOST=0.0.0.0 #<--- If you don't set this to 0.0.0.0, jetty only listen on localhost " >> /etc/default/jetty

echo "JETTY_LOGS=/opt/jetty/logs/ " >> /etc/default/jetty

#MySQL Installation

yum install mysql.x86_64
y
yum install mysql-server.x86_64
y
/etc/init.d/mysqld start