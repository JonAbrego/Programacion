using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Indicadores.Models;
using System.Configuration;
using System.Data.SqlClient;
using Newtonsoft.Json;

namespace Indicadores.Controllers
{
    public class HomeController : Controller
    {

        Calificacion e = new Calificacion();
        SLA s1 = new SLA();
        Tiempo t = new Tiempo();
        Criticos c = new Criticos();
        Media m = new Media();
        Solucion sol = new Solucion();
        NewEncuestas ne = new NewEncuestas();

        /**
         * Metodo que devuelve la vista Index
         **/
        public ActionResult Index(){            
            return View();
        }

        /**
         * Metodo que devuelve la vista Index, una vez que se le ha pasado 
         * una fecha inicio y un fecha fin para poder extraer la informacion 
         * para asi poder pasar los valores necesarios para generar los json,  
         * y poder generar las graficas correspondientes
         **/
        [HttpPost]
        public ActionResult Index(FormCollection form)
        {
            string s = form["from"]; // Recibe el valor del input dentro del form con el id="from"
            string t = form["to"];   // Recibe el valor del input dentro del form con el id="to"
            string fechaS = "";      // variables en la cual almacenaremos nuestra fecha de inicio
            string fechaT = "";      // variables en la cual almacenaremos nuestra fecha fin       
            try
            {
                fechaS = s.Substring(3, 3);  // Obtenemos el dia del input con id="form"
                fechaS += s.Substring(0, 3); // Obtenemos el mes del input con id="form"
                fechaS += s.Substring(6, 4); // Obtenemos el año del input con id="form", dejando nuestra fecha con formato dd/mm/yyyy
                fechaT = t.Substring(3, 3);  // Obtenemos el dia del input con id="to" 
                fechaT += t.Substring(0, 3); // Obtenemos el mes del input con id="to"
                fechaT += t.Substring(6, 4); // Obtenemos el año del input con id="to", dejando nuestra fecha con formato dd/mm/yyyy
                
                TempData["g"] = ne.select(fechaS, fechaT, "atencion"); // Creamos una variable la cual pasaremos a la vista, la cual llevara informacion obtenida de la base de datoa
                TempData["h"] = ne.select(fechaS, fechaT, "resolvio");
                TempData["i"] = ne.select(fechaS, fechaT, "cordial");
                TempData["j"] = ne.select(fechaS, fechaT, "calificacion");
                TempData["k"] = e.select(fechaS, fechaT);
            }
            catch (ArgumentOutOfRangeException e)
            {
                System.Diagnostics.Debug.WriteLine("{0} First exception caught.", e);               
            }
            return View();
        }

        /**
         * Metodo que devuelve la vista Plazos
         **/
        public ActionResult Plazos()
        {
            TempData["f"] = sol.select(); //Variable la cual usaremos para llenar nuetsro select en la vista
            return View();
        }
        
        /**
         * Metodo el cual realiza la extraccion necesaria de informacion para poder graficar
         * donde recibe form proveniente de la vista el cual ya trae consigo la informacion 
         * requerida como es el mes, ano, y solucion
         **/
        [HttpPost]
        public ActionResult Plazos(FormCollection form)
        {
            TempData["f"] = sol.select(); //Variable la cual usaremos para llenar nuetsro select en la vista
            string year = form["from"]; // Recibe el valor del input dentro del form con el id="from"
            string month = form["Mes"]; // Recibe el valor del input dentro del form con el id="mes"
            string solucion = form["sel"]; // Recibe el valor del select dentro del form con el id="sel"         
            try {
                TempData["a"] = year; //Varaible usada en la vista 
                TempData["m"] = month;
                TempData["datos"] = s1.select(year, month,solucion); //Variable que sera usada en la vista con la informacion necesaria
            }
                catch (SqlException e)
            {
                System.Diagnostics.Debug.WriteLine("{0} First exception caught.", e);               
            }
            return View();
        }


        /**
         * Metodo que devuelve la vista Tiempo
         **/
        public ActionResult Tiempo()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Tiempo(FormCollection form)
        {
            string year = form["from"]; // Recibe el valor del input dentro del form con el id="from"
            string month = form["Mes"]; // Recibe el valor del input dentro del form con el id="Mes"
            try {
                TempData["datos"] = t.select(year, month); //Variable que sera usada en la vista con la informacion necesaria
            }
            catch (SqlException e)
            {
                System.Diagnostics.Debug.WriteLine("{0} First exception caught.", e);
            }
            return View();
        }


        /**
         * Metodo que devuelve la vista Criticos
         **/
        public ActionResult Criticos()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Criticos(FormCollection form)
        {
            string year = form["from"];// Recibe el valor del input dentro del form con el id="from"
            string month = form["Mes"];// Recibe el valor del input dentro del form con el id="Mes"
            try
            {
                TempData["datos"] = c.select(year, month);//Variable que sera usada en la vista con la informacion necesaria
            }
            catch (SqlException e)
            {
                System.Diagnostics.Debug.WriteLine("{0} First exception caught.", e);
            }
            return View();
        }

        /**
         * Metodo que devuelve la vista Media
         **/
        public ActionResult Media()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Media(FormCollection form)
        {
            string year = form["from"];// Recibe el valor del input dentro del form con el id="from"
            string month = form["Mes"];// Recibe el valor del input dentro del form con el id="Mes"
            try
            {
                TempData["datos"] = m.select(year, month);//Variable que sera usada en la vista con la informacion necesaria
            }
            catch (SqlException e)
            {
                System.Diagnostics.Debug.WriteLine("{0} First exception caught.", e);
            }
            return View();
        }        

	}
}