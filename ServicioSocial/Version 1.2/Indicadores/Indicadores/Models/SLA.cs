using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Indicadores.Models
{
    public class SLA
    {

        public SLA()
        {
            data = new List<int>();
        }

        //Atributos de la calse
        public String name { get; set; }
        public List<int> data { get; set; }

        /**
        * Metodo que devuelve una lista de objetos SLA, los cuales son extraidos 
        * de la base de datos tomando como criterio una fecha en particular definido por 
        * el ano y mes, que son pasados como parametros respectivamente, ademas de un parametro
        * extra el cual sirve para delimitar a la parte encargada de la solucion
        **/
        public List<SLA> select(string y, string m,string s)
        {
            SLA a = null;
            List<SLA> mes = new List<SLA>();

            SqlConnection conn;
            SqlCommand cmd = new SqlCommand();
            cmd.CommandTimeout = 600;
            SqlDataReader reader;
            conn = new SqlConnection(ConfigurationManager.ConnectionStrings["Conexion2"].ConnectionString);
            try
            {
                cmd.CommandText = "SELECT SUM(Total),SUM([En Tiempo]),SUM([Fuera de Tiempo]),[Año de Creación],[Categoria de Solución],[Mes de Creación] FROM [v_indicador_inicdentes_resueltos_plazo_SLA] WHERE [Año de Creación] = " + y + " AND [Mes de Creación] = " + m + "AND [Categoria de Solución]='" + s + "' GROUP BY [Año de Creación],[Categoria de Solución],[Mes de Creación] ";
                cmd.Connection = conn;
                conn.Open();
                reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        a = new SLA();
                        //System.Diagnostics.Debug.WriteLine(reader.GetInt32(0));
                        //System.Diagnostics.Debug.WriteLine(reader.GetInt32(1));                        
                        a.name = reader.GetString(4) + "-Año: " + reader.GetInt32(3).ToString() + "-Mes: " + reader.GetInt32(5).ToString();
                        a.data.Add(reader.GetInt32(0));
                        a.data.Add(reader.GetInt32(1));
                        a.data.Add(reader.GetInt32(2));
                        mes.Add(a);
                    }

                }
            }
            catch (Exception ex)
            {
                System.Diagnostics.Debug.WriteLine("Algo fallo o_O",ex);
            }
            finally
            {
                conn.Close();
            }
            return mes;
        }


    }
}