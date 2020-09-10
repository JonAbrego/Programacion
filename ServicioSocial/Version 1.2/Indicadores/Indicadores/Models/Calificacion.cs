using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Indicadores.Models
{
   
    public class Calificacion
    {                    

        public Calificacion(){}

        //Atributos de la calse
        public String Solucion { get; set; }
        public double calificacion  { get; set; }
        public int cantidad { get; set; }
        
        /**
         * Metodo que devuelve una lista de objetos Calificacion, los cuales son extraidos 
         * de la base de datos tomando como criterio un intervalo de fecha representados por 
         * los parametros s y t, donde son fecha inicion y fecha fin respectivamente
         **/
        public List<Calificacion> select(string s, string t)
        {
            Calificacion a = null; 
            List<Calificacion> sem = new List<Calificacion>();
            SqlConnection conn;
            SqlCommand cmd = new SqlCommand();
            SqlDataReader reader;
            conn = new SqlConnection(ConfigurationManager.ConnectionStrings["Conexion"].ConnectionString);
            try
            {
                cmd.CommandText = "SELECT  [Categoria de Solución], Calificacion,COUNT(calificacion) "+
                    " FROM Encuestas WHERE calificacion is not null AND [Categoria de Solución] is not null "+
                    " AND  [Fecha de Creacion] BETWEEN '"+ s +"' AND '"+ t +"'"+
                    " GROUP BY  [Categoria de Solución],calificacion ORDER BY [Categoria de Solución],calificacion DESC";
                cmd.Connection = conn;
                conn.Open();
                reader = cmd.ExecuteReader();
                if (reader.HasRows)
                {
                    while (reader.Read())
                    {
                        a = new Calificacion();                        
                        a.Solucion = reader.GetString(0);
                        a.calificacion = reader.GetDouble(1);
                        a.cantidad = reader.GetInt32(2);                        
                        sem.Add(a);
                    }

                }
            }
            catch (NullReferenceException ex)
            {
                System.Diagnostics.Debug.WriteLine("Algo fallo o_O",ex);
            }
            finally
            {
                conn.Close();
            }
            return sem;
        }
    }   
}