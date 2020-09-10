using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Indicadores.Models
{
    public class Criticos
    {

        public Criticos() { }

        //Atributos de la calse
        public String name { get; set; }
        public int y { get; set; }


        /**
        * Metodo que devuelve una lista de objetos Criticos, los cuales son extraidos 
        * de la base de datos tomando como criterio un intervalo de fecha representados por 
        * los parametros s y t, donde son fecha inicion y fecha fin respectivamente
        **/
        public List<Criticos> select(string y, string m)
        {
            Criticos a = null;
            List<Criticos> mes = new List<Criticos>();

            SqlConnection conn;
            SqlCommand cmd = new SqlCommand();
            cmd.CommandTimeout = 600;
            SqlDataReader reader;
            conn = new SqlConnection(ConfigurationManager.ConnectionStrings["Conexion2"].ConnectionString);
            try
            {
                cmd.CommandText = "select TOP 10 Falla, [Promedio en Minutos] from [dbo].[v_indicador_incidentes_causan_interrupcion_procesos_criticos] where Mes is not null and Año is not null and [Promedio en Minutos] is not null and Año = " + y + " and Mes = " + m + " order by [Promedio en Minutos] desc";
                cmd.Connection = conn;
                conn.Open();
                reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        a = new Criticos();
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