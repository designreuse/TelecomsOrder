<%--
  Created by IntelliJ IDEA.
  User: Bingdor
  Date: 2015/11/25
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar-default navbar-static-side" role="navigation" style="height: 100%;overflow: hidden;">
    <div class="sidebar-collapse">
        <div class="nav-header">
            <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="images/common/profile_small.jpg"/>
                             </span>
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs">
                                <strong class="font-bold">${sessionScope._SESSION_USER_KEY_.name}</strong><b class="caret"></b>
                             </span>
                            <span class="text-muted text-xs block">${sessionScope._SESSION_USER_KEY_.org.name}</span>

                </a>
                <ul class="dropdown-menu animated fadeInRight m-t-xs">
                    <li><a href="profile.html">个人中心</a></li>
                    <li><a href="contacts.html">联系人</a></li>
                    <li><a href="mailbox.html">企业信箱</a></li>
                    <li class="divider"></li>
                    <li><a href="j_spring_security_logout">注销</a></li>
                </ul>
            </div>
        </div>
        <ul class="nav metismenu" id="side-menu">
        </ul>
    </div>
</nav>