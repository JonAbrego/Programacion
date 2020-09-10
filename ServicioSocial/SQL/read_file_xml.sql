/*
* Version 1.3
* Jonathan Abrego
*/
USE [Pruebas]
DECLARE @XmlDocumentHandle int  
DECLARE @XmlFile XML

SELECT @XmlFile = BulkColumn FROM  OPENROWSET(BULK 'C:\Users\Professional\Desktop\Memoria_Correo_Marzo-02.xml', SINGLE_BLOB) x;

EXEC sp_xml_preparedocument @XmlDocumentHandle OUTPUT, @XmlFile 

INSERT INTO [dbo].[Otra](
	DisplayName1, DisplayName2, [Rule], Scale, Count, Min, Max, 
	Average, Standard_Deviation, Created, StartDate, EndDate, BusinessHours)
SELECT 	
	DisplayName1, 
	SUBSTRING(DisplayName2,12,250) AS DisplayName2,
	[Rule],
	Scale,
	Count,
	Min,
	Max,
	Average,
	(StandardDeviation) AS Standard_Deviation,
	--Created,	
	--StartDate,
	--EndDate,	
	convert(datetime,substring(Created,0,11) +' '+	
	substring(Created,12,5)+':00'+upper(replace(replace(substring(Created,17,8),' ',''),'.',''))) AS Created,
	convert(datetime,substring(StartDate,0,11) +' '+	
	substring(StartDate,12,5)+':00'+upper(replace(replace(substring(StartDate,17,8),' ',''),'.',''))) AS StartDate,
	convert(datetime,substring(EndDate,0,11) +' '+	
	substring(EndDate,12,5)+':00'+upper(replace(replace(substring(EndDate,17,8),' ',''),'.',''))) AS EndDate,	
	BusinessHours
FROM OPENXML (@XmlDocumentHandle, '/Report/Charts/GroupList_Details_Group_Collection/Chart/DataTable/
SeriesCollection/Series/DataTable_InstanceMainGroup_Collection/Target/Instance/Instance/
TargetCollection/Target/Objects/Object',2)  
WITH (
DisplayName1 nvarchar(250),
DisplayName2 nvarchar(250),
[Rule] nvarchar(250) '../../../../../../../../Rule',
Scale int '../../../../../../../../Scale',
[Count] int '../../../../../../../../Count', 
[Min] float '../../../../../../../../Min',
[Max] float '../../../../../../../../Max',
Average float '../../../../../../../../Average',
StandardDeviation float '../../../../../../../../StandardDeviation',
Created nvarchar(250) '../../../../../../../../../../../../../../Created', 
StartDate nvarchar(250) '../../../../../../../../../../../../../../StartDate',
EndDate nvarchar(250) '../../../../../../../../../../../../../../EndDate',
BusinessHours nvarchar(250) '../../../../../../../../../../../../../../BusinessHours'
)

EXEC sp_xml_removedocument @XmlDocumentHandle