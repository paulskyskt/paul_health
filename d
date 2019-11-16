[1mdiff --git a/health_parent/health_service_provider/src/main/java/com/paul/service/impl/MenuServiceImpl.java b/health_parent/health_service_provider/src/main/java/com/paul/service/impl/MenuServiceImpl.java[m
[1mindex 6d29e8b..9a9974d 100644[m
[1m--- a/health_parent/health_service_provider/src/main/java/com/paul/service/impl/MenuServiceImpl.java[m
[1m+++ b/health_parent/health_service_provider/src/main/java/com/paul/service/impl/MenuServiceImpl.java[m
[36m@@ -1,19 +1,11 @@[m
 package com.paul.service.impl;[m
[31m-[m
 import com.alibaba.dubbo.config.annotation.Service;[m
 import com.paul.dao.MenuDao;[m
[31m-import com.paul.pojo.Aside;[m
[31m-import com.paul.pojo.Children;[m
 import com.paul.pojo.Menu;[m
 import com.paul.service.MenuService;[m
 import org.springframework.beans.factory.annotation.Autowired;[m
[31m-import org.springframework.security.core.context.SecurityContext;[m
[31m-import org.springframework.security.core.context.SecurityContextHolder;[m
[31m-import org.springframework.security.core.userdetails.User;[m
 import org.springframework.transaction.annotation.Transactional;[m
[31m-[m
 import java.util.ArrayList;[m
[31m-import java.util.Arrays;[m
 import java.util.List;[m
 [m
 @Service(interfaceClass = MenuService.class)[m
