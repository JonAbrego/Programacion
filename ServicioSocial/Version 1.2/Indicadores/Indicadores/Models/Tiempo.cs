using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Indicadores.Models
{
    public class Tiempo
    {
        public Tiempo() { }

        //Atributos de la calse
        public String name { get; set; }
        public int y { get; set; }

        /**
       * Metodo que devuelve una lista de objetos Tiempo, los cuales son extraidos 
       * de la base de datos tomando como criterio una fecha en particular definido por 
       * el ano y mes, que son pasados como parametros respectivamente
       **/
        public List<Tiempo> select(string y, string m)
        {
            Tiempo a = null;
            List<Tiempo> mes = new List<Tiempo>();

            SqlConnection conn;
            SqlCommand cmd = new SqlCommand();
            cmd.CommandTimeout = 600;
            SqlDataReader reader;
            conn = new SqlConnection(ConfigurationManager.ConnectionStrings["Conexion2"].ConnectionString);
            try
            {
                cmd.CommandText = "select TOP 10 Falla, [Promedio en Minutos] from [dbo].[v_indicador_tiempo_entre_incidentes] where Año = " + y + " and Mes =" + m + " order by [Promedio en Minutos] desc";
                cmd.Connection = conn;
                conn.Open();
                reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        a = new Tiempo();                                                
                        a.name = reader.GetString(0);
                        a.y = reader.GetInt32(1);
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