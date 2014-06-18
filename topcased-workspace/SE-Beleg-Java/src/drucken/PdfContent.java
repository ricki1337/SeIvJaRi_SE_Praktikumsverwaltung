package drucken;

public class PdfContent {
	static String pdfcontent = 	"<html> " + 
								"	<head> " + 
								"		<title>${title}</title> " + 
								"		<style type=\"text/css\"> " + 
								"	 " + 
								"		table { border-collapse:collapse; } " + 
								" " + 
								"		th{	border:1px solid black;} " + 
								"			 " + 
								"		td{max-width:30%;min-width:1%} " + 
								"			 " + 
								"		th,td {padding:5px;} " + 
								" " + 
								"	</style> " + 
								"	</head> " + 
								"	 " + 
								"	<body> " + 
								"		<div> " + 
								"			<hr /> " + 
								"			<table align=\"center\" border=\"0\" style=\"width: 768;\"> " + 
								"				<tbody> " + 
								"					<tr> " + 
								"						<td> " + 
								"							<span><strong>HTW Dresden (FH)<br /> " + 
								"							Fakult&auml;t Informatik/Mathematik</strong></span></td> " + 
								"						<td style=\"text-align: right;\"> " + 
								"							<span><strong>Prof. A. Beck<br /> " + 
								"							Praktikumsverantwortlicher</strong></span></td> " + 
								"					</tr> " + 
								"					<tr> " + 
								"						<td> " + 
								"							<font size=3> " + 
								"								Friedrich-List-Platz 1<br /> " + 
								"								01069 Dresden<br /> " + 
								"								Tel.: 0351 462 ext. 0 " + 
								"							</font> " + 
								"						</td> " + 
								"						<td style=\"text-align: right;\"> " + 
								"							<#-- Create constructor object --> " + 
								"							<#assign objectConstructor = \"freemarker.template.utility.ObjectConstructor\"?new()> " + 
								"							 " + 
								"							<#-- Call calendar constructor --> " + 
								"							<#assign clock = objectConstructor(\"java.util.GregorianCalendar\")> " + 
								"							 " + 
								"							<#-- Call formatter constructor --> " + 
								"							<#assign ddmmyy = objectConstructor(\"java.text.SimpleDateFormat\",\"dd. MMMMMMMM yyyy\")> " + 
								"							 " + 
								"							<#-- Call getTime method to return the date in milliseconds--> " + 
								"							<#assign date = clock.getTime()> " + 
								"							 " + 
								"							<#-- Call format method to pretty print the date --> " + 
								"							<#assign now = ddmmyy.format(date)> " + 
								"							 " + 
								"							<#-- Display date --> " + 
								"							<font size=3>${now}</font> " + 
								"						</td> " + 
								"					</tr> " + 
								"				</tbody> " + 
								"			</table> " + 
								"			<hr /> " + 
								"			<h2 align=\"center\"> " + 
								"				<span style=\"font-family:lucida sans unicode,lucida grande,sans-serif;font-size: 20.0px;\"><strong>${headline}</strong></span></h2> " + 
								"			 " + 
								"			<table id=\"inhalt\" align=\"center\" style=\"width: 750;\"> " + 
								"			<colgroup> " + 
								"			    <col> " + 
								"			    <col> " + 
								"			    <col> " + 
								"			    <col> " + 
								"			    <col> " + 
								"			    <col> " + 
								"			  </colgroup> " + 
								"				<thead> " + 
								"					<tr> " + 
								"						<th style=\"text-align: center;\" scope=\"col\"> " + 
								"							ID</th> " + 
								"						<th scope=\"col\" style=\"text-align: left;\"> " + 
								"							Matrikel</th> " + 
								"						<th scope=\"col\" style=\"text-align: left;\"> " + 
								"							Name</th> " + 
								"						<th scope=\"col\" style=\"text-align: left;\"> " + 
								"							E-Mail</th> " + 
								"						<th scope=\"col\" style=\"text-align: left;\"> " + 
								"							Studiengruppe</th> " + 
								"						<th scope=\"col\" style=\"text-align: left;\"> " + 
								"							Firma</th> " + 
								"					</tr> " + 
								"				</thead> " + 
								"				<tbody> " + 
								"					<#list tableData as student> " + 
								"						<tr> " + 
								"							<td style=\"text-align: center;\">${student_index + 1}.</td> " + 
								"		    				<td>${student.mat}</td> " + 
								"		    				<td>${student.name}</td> " + 
								"		    				<td>${student.mail}</td> " + 
								"		    				<td>${student.gruppe}</td> " + 
								"		    				<td>${student.firma}</td> " + 
								"						</tr> " + 
								"					</#list> " + 
								"				</tbody> " + 
								"			</table> " + 
								"		</div> " + 
								"	</body> " + 
								"</html> " ;
	
	static public String getPdfContent(){
		return pdfcontent;
	}
	


}
