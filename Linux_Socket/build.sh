#sudo apt install default-libmysqlclient-dev
#sudo vi /etc/mysql/mariadb.conf.d/50-server.cnf bind-address 주석
#sudo service mysql restart
gcc st_db.c -o st_db -lmysqlclient
