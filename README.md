Md5 Password Encoder for GeoNetwork
===================================

A MD5 password encoder for [GeoNetwork](http://geonetwork-opensource.org/), which has been developed and tested for version 2.10.3. It is probable that it works with other versions as well.

Installation
------------

* Clone the repository `git clone https://github.com/webrian/GeoNetworkMd5PasswordEncoder.git`
* or download it from [GitHub](https://github.com/webrian/GeoNetworkMd5PasswordEncoder/archive/master.zip)
* Open the [NetBeans](https://netbeans.org/) project included in the repository and `Clean and Build (Shift+F11)` it
* Copy the resulting `GeoNetworkMd5PasswordEncoder` to the `/WEB-INF/lib` directory in the GeoNetwork webapp
* Update the configuration settings in `/WEB-INF/config-security-core.xml`

Replace the following lines


    <bean class="org.springframework.security.crypto.password.StandardPasswordEncoder" id="geonetworkEncoder">
        <constructor-arg value="SHA-256"/>
        <constructor-arg value="${passwordSalt}"/>
    </bean>


with

    <bean class="ch.unibe.cde.geonet.kernel.security.Md5PasswordEncoder" id="geonetworkEncoder"/>
    
* Restart Tomcat
