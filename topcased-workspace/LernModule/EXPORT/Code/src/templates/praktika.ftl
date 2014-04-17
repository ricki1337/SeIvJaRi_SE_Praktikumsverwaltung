<html>
	<head>
		<title>${title}</title>
	</head>
	<body>
		<div>
			<hr />
			<table align="center" border="0" cellpadding="1" cellspacing="1" style="width: 100%;">
				<tbody>
					<tr>
						<td>
							<span style="font-size:14px;"><span style="font-family: lucida sans unicode,lucida grande,sans-serif;"><strong>HTW Dresden (FH)<br />
							Fakult&auml;t Informatik/Mathematik</strong></span></span></td>
						<td style="text-align: right;">
							<span style="font-size:14px;"><strong><span style="font-family: lucida sans unicode,lucida grande,sans-serif;">Prof. A. Beck<br />
							Praktikumsverantwortlicher</span></strong></span></td>
					</tr>
					<tr>
						<td>
							<address>
								Friedrich-List-Platz 1<br />
								01069 Dresden<br />
								Tel.: 0351 462 ext. 0</address>
						</td>
						<td style="text-align: right;">
							<#-- Create constructor object -->
							<#assign objectConstructor = "freemarker.template.utility.ObjectConstructor"?new()>
							
							<#-- Call calendar constructor -->
							<#assign clock = objectConstructor("java.util.GregorianCalendar")>
							
							<#-- Call formatter constructor -->
							<#assign ddmmyy = objectConstructor("java.text.SimpleDateFormat","dd. MMMMMMMM yyyy")>
							
							<#-- Call getTime method to return the date in milliseconds-->
							<#assign date = clock.getTime()>
							
							<#-- Call format method to pretty print the date -->
							<#assign now = ddmmyy.format(date)>
							
							<#-- Display date -->
							${now}
						</td>
					</tr>
				</tbody>
			</table>
			<hr />
			<h1 align="center" data-mce-style="text-align: center;">
				<span style="font-family:lucida sans unicode,lucida grande,sans-serif;"><span style="font-size: 36px;">${title}</span></span></h1>
			<h2 align="center" data-mce-style="text-align: center;">
				<span style="font-family:lucida sans unicode,lucida grande,sans-serif;"><strong><span data-mce-style="font-size: 20.0pt;">${headline}</span></strong></span></h2>
			<table align="center" border="0" cellpadding="1" cellspacing="1" style="width: 80%;">
				<thead>
					<tr>
						<th style="text-align: center;" scope="col">
							ID</th>
						<th scope="col" style="text-align: left;">
							Matrikelnummer</th>
						<th scope="col" style="text-align: left;">
							Nachname</th>
						<th scope="col" style="text-align: left;">
							Vormame</th>
						<th scope="col" style="text-align: left;">
							Studiengruppe</th>
						<th scope="col" style="text-align: left;">
							Firma</th>
					</tr>
				</thead>
				<tbody>
					<#list tableData as student>
						<tr>
							<td style="text-align: center;">${student_index + 1}.</td>
		    				<td>${student.mat}</td>
		    				<td>${student.name}</td>
		    				<td>${student.mail}</td>
		    				<td>${student.gruppe}</td>
		    				<td>${student.firma}</td>
						</tr>
					</#list>
				</tbody>
			</table>
		</div>
	</body>
</html>
