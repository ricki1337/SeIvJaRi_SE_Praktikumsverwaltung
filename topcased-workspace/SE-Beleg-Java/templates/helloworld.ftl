<html>
<head>
  <title>${title}</title>
</head>
<body>
  <h1>${title}</h1>

  <p>${exampleObject.mat} mit offenem ${exampleObject.name}</p>
   <table border="1">
   	<tr>
	 	<th>ID</th>
	 	<th>Matrikelnummer</th>
	    <th>Name</th>
	    <th>Mail</th>
	    <th>Studiengruppe</th>
	    <th>Firma</th>	    
	  </tr>
	<#list students as student>
      <tr>
	    <td>${student_index + 1}.</td>
	    <td>${student.mat}</td>
	    <td>${student.name}</td>
	    <td>${student.mail}</td>
	    <td>${student.gruppe}</td>
	    <td>${student.firma}</td>
	  </tr>
    </#list>
   </table>
    

</body>
</html> 