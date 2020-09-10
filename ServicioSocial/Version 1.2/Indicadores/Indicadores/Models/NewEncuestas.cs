using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Indicadores.Models
{
    public class NewEncuestas
    {
        //Atributos de la calse
        public String name { get; set; }
        public List<double> data { get; set; }

        public NewEncuestas() {
            data = new List<double>();
        }


        public List<NewEncuestas> select(string s, string t, string n)
        {
            NewEncuestas a = null;
            List<NewEncuestas> mes = new List<NewEncuestas>();

            SqlConnection conn;
            SqlCommand cmd = new SqlCommand();
            cmd.CommandTimeout = 600;
            SqlDataReader reader;
            conn = new SqlConnection(ConfigurationManager.ConnectionStrings["Conexion"].ConnectionString);
            try
            {
                if (n.Equals("atencion"))
                {
                    cmd.CommandText = "select [Categoria de Solución], sum(t1.adecuada) as adecuada,sum(t1.rapida) as rapida,sum(t1.lenta) as lenta "+
                    " FROM(SELECT [Categoria de Solución], count(atencion)AS adecuada,0 as rapida,0 AS lenta FROM Encuestas "+
                    " WHERE atencion is not null AND [Fecha de Creacion] BETWEEN '"+ s +"' AND '"+ t +"' and atencion ='adecuada' "+
                    " GROUP BY  [Categoria de Solución],atencion UNION SELECT [Categoria de Solución],0 as adecuada,count(atencion)AS rapida,0 AS lenta "+
                    " FROM Encuestas WHERE atencion is not null AND [Fecha de Creacion] BETWEEN '"+ s +"' AND '"+ t +"' and atencion ='rápida' " +
                    " GROUP BY  [Categoria de Solución],atencion UNION  SELECT [Categoria de Solución],0 as adecuada,0 as rapida,count(atencion)AS lenta "+
                    " FROM Encuestas WHERE atencion is not null AND [Fecha de Creacion] BETWEEN '"+ s +"' AND '"+ t +"' and atencion ='lenta' " +
                    " GROUP BY  [Categoria de Solución],atencion) as t1 WHERE [Categoria de Solución] IS NOT NULL GROUP BY  [Categoria de Solución] ";
                }
                else
                {
                    if (n.Equals("resolvio"))
                    {
                        cmd.CommandText = "select [Categoria de Solución], sum(t1.si) as si,sum(t1.parcialmente) as parcialmente,sum(t1.[no]) as [no] " +
                    " FROM(SELECT [Categoria de Solución], count(resolvio)AS si,0 as parcialmente,0 AS [no] FROM Encuestas " +
                    " WHERE resolvio is not null AND [Fecha de Creacion] BETWEEN '" + s + "' AND '" + t + "' and resolvio ='si' " +
                    " GROUP BY  [Categoria de Solución],resolvio UNION SELECT [Categoria de Solución],0 as si,count(resolvio)AS parcialmente,0 AS [no] " +
                    " FROM Encuestas WHERE resolvio is not null AND [Fecha de Creacion] BETWEEN '" + s + "' AND '" + t + "' and resolvio ='parcialmente' " +
                    " GROUP BY  [Categoria de Solución],resolvio UNION  SELECT [Categoria de Solución],0 as si,0 as parcialmente,count(resolvio) AS [no] " +
                    " FROM Encuestas WHERE resolvio is not null AND [Fecha de Creacion] BETWEEN '" + s + "' AND '" + t + "' and resolvio ='no' " +
                    " GROUP BY  [Categoria de Solución],resolvio) as t1 WHERE [Categoria de Solución] IS NOT NULL GROUP BY  [Categoria de Solución] ";
                    }
                    else
                    {
                        if (n.Equals("cordial"))
                        {
                            cmd.CommandText = "select [Categoria de Solución], sum(t1.si) as si,sum(t1.[no]) as [no] FROM " +
                        " (SELECT [Categoria de Solución], count(cordial) AS si,0 AS [no] " +
                        " FROM Encuestas WHERE cordial is not null AND [Fecha de Creacion] BETWEEN '" + s + "' AND '" + t + "' and cordial ='si'  " +
                        " GROUP BY  [Categoria de Solución],cordial " +
                        " UNION " +
                        " SELECT [Categoria de Solución],0 as si,count(cordial)AS [no] " +
                        " FROM Encuestas WHERE cordial is not null AND [Fecha de Creacion] BETWEEN '" + s + "' AND '" + t + "' and cordial ='no' " +
                        " GROUP BY  [Categoria de Solución],cordial) as t1 " +
                        " WHERE [Categoria de Solución] IS NOT NULL " +
                        " GROUP BY  [Categoria de Solución] ";
                        }
                        else
                        {
                            cmd.CommandText = " SELECT t.[Categoria de Solución], sum(t.Mult_Calf)/SUM(t.Cantidad) AS Promedio_Calificacion from " +
                                " (select [Categoria de Solución],COUNT(calificacion) AS Cantidad, (Calificacion*COUNT(calificacion)) AS Mult_Calf " +
                                " from Encuestas " +
                                " WHERE calificacion is not null and [Categoria de Solución] is not null and  [Fecha de Creacion]  BETWEEN '" + s + "' AND '" + t + "' " +
                                " GROUP BY  [Categoria de Solución],calificacion " +
                                " ) AS t " +
                                " GROUP BY  [Categoria de Solución] ";
                        }
                  }
                    
                }
                
                
                cmd.Connection = conn;
                conn.Open();
                reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        a = new NewEncuestas();
                        //System.Diagnostics.Debug.WriteLine(reader.GetInt32(0));
                        //System.Diagnostics.Debug.WriteLine(reader.GetInt32(1));                        
                        a.name = reader.GetString(0);
                        if (n.Equals("calificacion"))
                            a.data.Add(Math.Round(reader.GetDouble(1), 2));                       
                        
                        if (!n.Equals("calificacion"))
                        {
                            a.data.Add(reader.GetInt32(1));
                            a.data.Add(reader.GetInt32(2));
                            if (!n.Equals("cordial"))
                                a.data.Add(reader.GetInt32(3));
                        }
                        mes.Add(a);
                    }

                }
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Algo fallo o_O", ex);
            }
            finally
            {
                conn.Close();
            }
            return mes;
        }


    }
}