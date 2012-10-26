export GRADLE_HOME=/usr/local/gradle-1.2/
/usr/local/gradle-1.2/bin/gradle $@
Xvfb :1 $@
export DISPLAY=:1 $@

