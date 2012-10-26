export GRADLE_HOME=/usr/local/gradle-1.2/
/usr/local/gradle-1.2/bin/gradle $@
if(GO_SERVER_URL=="https://10.10.4.101:8154/go/"){
	Xvfb :1
	export DISPLAY=:1
}
