<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/3/3
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Destroydrop &raquo; Javascripts &raquo; Tree</title>

    <link rel="StyleSheet" href="css/dtree.css" type="text/css"/>
    <script type="text/javascript" src="js/dtree.js"></script>
</head>
<h1><a href="/">Destroydrop</a> &raquo; <a href="/javascripts/">Javascripts</a> &raquo; <a href="/javascripts/tree/">Tree</a>
</h1>

<h2>Example</h2>

<div class="dtree">

    <p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>

    <script type="text/javascript">
        <!--

        d = new dTree('d');
        d.add(0, -1, 'My example tree');
        d.add(1, 0, 'Node 1', 'example01.jsp');
        d.add(2, 0, 'Node 2', 'example01.jsp');
        d.add(3, 1, 'Node 1.1', 'example01.jsp');
        d.add(4, 0, 'Node 3', 'example01.jsp');
        d.add(5, 3, 'Node 1.1.1', 'example01.jsp');
        d.add(6, 5, 'Node 1.1.1.1', 'example01.jsp');
        d.add(7, 0, 'Node 4', 'example01.jsp');
        d.add(8, 1, 'Node 1.2', 'example01.jsp');
        d.add(9, 0, 'My Pictures', 'example01.jsp', 'Pictures I\'ve taken over the years', '', '', 'img/imgfolder.gif');
        d.add(10, 9, 'The trip to Iceland', 'example01.jsp', 'Pictures of Gullfoss and Geysir');
        d.add(11, 9, 'Mom\'s birthday', 'example01.jsp');
        d.add(12, 0, 'Recycle Bin', 'example01.jsp', '', '', 'img/trash.gif');

        document.write(d);

        //-->
    </script>

</div>

<p><a href="mailto&#58;drop&#64;destroydrop&#46;com">&copy;2002-2003 Geir Landr&ouml;</a></p>

</body>
</html>
