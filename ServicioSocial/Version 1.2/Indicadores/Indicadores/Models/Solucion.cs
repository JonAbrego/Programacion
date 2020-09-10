using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Data.SqlTypes;
using System.Linq;
using System.Web;

namespace Indicadores.Models
{
    public class Solucion
    {
        public Solucion()
        {

        }

        //Atributos de la calse
        public string name { get; set; }

        /**
        * Metodo que devuelve una lista de objetos Solucion, usados para
        * rellenar un select
        **/
        public List<Solucion> select()
        {
            Solucion a = null;
            List<Solucion> sol = new List<Solucion>();

            SqlConnection conn;
            SqlCommand cmd = new SqlCommand();
            cmd.CommandTimeout = 600;
            SqlDataReader reader;
            conn = new SqlConnection(ConfigurationManager.ConnectionStrings["Conexion2"].ConnectionString);
            try
            {
                cmd.CommandText = "select distinct([Categoria de Solución]) FROM [DWDataMart].[dbo].[v_indicador_inicdentes_resueltos_plazo_SLA] WHERE [Categoria de Solución] IS NOT NULL";
                cmd.Connection = conn;
                conn.Open();
                reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        a = new Solucion();
                        //System.Diagnostics.Debug.WriteLine(reader.GetInt32(0));
                        //System.Diagnostics.Debug.WriteLine(reader.GetInt32(1));                        
                        a.name = reader.GetString(0);
                        sol.Add(a);
                    }

                }
            }
            catch (SqlNullValueException s)
            {
                System.Diagnostics.Debug.WriteLine("Tomo un valor null",s);
            }
            finally
            {
                conn.Close();
            }
            return sol;
        }
    }
}