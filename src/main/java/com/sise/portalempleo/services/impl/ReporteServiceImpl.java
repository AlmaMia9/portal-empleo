package com.sise.portalempleo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.sise.portalempleo.entities.OfertaTrabajo;
import com.sise.portalempleo.repositories.OfertaTrabajoRepository;
import com.sise.portalempleo.services.ReporteService;
@Service
public class ReporteServiceImpl implements ReporteService {

    //private static String COLOR_HEADER ="#12ff00";
    //private static String COLOR_PRIMARY ="#12ff00";
    @Autowired
    private OfertaTrabajoRepository ofertaTrabajoRepository;

    @Override   
    public byte[] generarOfertaTrabajoPdf(Integer idOfertaTrabajo) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); 

        OfertaTrabajo ofertaTrabajo = ofertaTrabajoRepository.findById(idOfertaTrabajo).orElse(null);

        HtmlConverter.convertToPdf( new StringBuilder()
                                        .append("<!DOCTYPE html>")
                                        .append("<hmtl>")
                                            .append("<head>")
                                              .append("<style>")
                                                 .append(generateCSS())
                                            .append("</style>")
                                        .append("</head>")
                                        .append("<body>")
                                                .append(generateHeaderPdf())
                                                .append(generateOfertaTrabajoCabecera(ofertaTrabajo))
                                                .append(generateOfertaTrabajoDetalle(ofertaTrabajo))
                                                .append(generateFooter())
                                        .append("</body>")
                                        .append("</html>")
                                        .toString()
                                        ,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    private String generateHeaderPdf(){
        StringBuilder builder = new StringBuilder();

        builder.append("<div id=\"main-container-header-pdf\">");

           builder.append("<div id=\"container-title-header-pdf\">");
           builder.append("<p class=titulo>TRABAJO COMPU</p>");
           builder.append("</div>");    

        builder.append("<div id=\"container-datos-header-pdf\">");
        builder.append("<p>OFERTA DE TRABAJO</p>");
        builder.append("<p>Lima, Perú</p>");
        builder.append("</div>");
        
        builder.append("</div>");

        return builder.toString();
    }

    private String generateOfertaTrabajoCabecera(OfertaTrabajo ofertaTrabajo){
        StringBuilder builder = new StringBuilder();
        builder.append("")

        .append("<div id=\"main-container-procab\">")
            .append("<div id=\"container\">")
            
            .append("<ul class=list-group")
            .append("<li class=list-group-item id=\"id_oferta_trabajo\">Id de Oferta de Trabajo</li>")
               .append("Para: "+ofertaTrabajo.getIdOfertaTrabajo())
            .append("<li class=list-group-item id=\"id_empleador\">Id del Empleador</li>")
               .append("Para: "+ofertaTrabajo.getEmpleador().getIdEmpleador())
            .append("<li class=list-group-item id=\"id_categoria_empleo\">Id de la Categoría Empleado</li>")
               .append("Para: "+ofertaTrabajo.getCategoriaEmpleo().getIdCategoriaEmpleo())
            .append("<li class=list-group-item id=\"titulo\">Titulo</li>")
               .append("Para: "+ofertaTrabajo.getTitulo())
            .append("<li class=list-group-item id=\"descripcion\">Descripción</li>")
               .append("Para: "+ofertaTrabajo.getDescripcion())
            .append("<li class=list-group-item id=\"disponibilidad_horaria\">Disponibilidad Horaria</li>")
               .append("Para: "+ofertaTrabajo.getDisponibilidadHoraria())
            .append("<li class=list-group-item id=\"turno\">Turno</li>")
               .append("Para: "+ofertaTrabajo.getTurno())
            .append("<li class=list-group-item id=\"modalidad\">Modalidad</li>")
               .append("Para: "+ofertaTrabajo.getModalidad())   
            .append("<li class=list-group-item id=\"salario\">Salario</li>")
               .append("Para: "+ofertaTrabajo.getSalario())
               .append("</ul>")   
            .append("</div>")


        .append("");
        return builder.toString();
    }

    private String generateOfertaTrabajoDetalle(OfertaTrabajo ofertaTrabajo){
        StringBuilder builder = new StringBuilder();
        builder.append("")

        .append("<div id=\"main-container-prodet\">")

            .append("<table class=tabla-reportes id=\"table\">")
            .append("<tr>")
                .append("<th>Departamento </th>")
                .append("<th>Provincia </th>")
                .append("<th>Distrito</th>")
                .append("<th>Fecha Publicación </th>")
                .append("<th>Fecha Vencimiento </th>")
                .append("<th>Requisitos </th>")
                .append("<th>Estado Oferta </th>")

            .append("</tr>")
                        .append("<td style=\"text-align: left;\">")
                        .append("<td>"+ofertaTrabajo.getDepartamento()+"</td>")
                        .append("<td>"+ofertaTrabajo.getProvincia()+"</td>")
                        .append("<td>"+ofertaTrabajo.getDistrito()+"</td>")
                        .append("<td>"+ofertaTrabajo.getFechaPublicacion()+"</td>")
                        .append("<td>"+ofertaTrabajo.getFechaVencimiento()+"</td>")
                        .append("<td>"+ofertaTrabajo.getRequisitos()+"</td>")
                        .append("<td>"+ofertaTrabajo.getEstadoOferta()+"</td>")
                    .append("</tr>")

            .append("</table>")

        .append("</div>")

        .append("");
        return builder.toString();
    }

    private String generateFooter(){
        StringBuilder builder = new StringBuilder();

        builder.append("<footer class=parte-baja id=\"footer\">");
        builder.append("<p><p>Dirección web: www.TRABAJOCOMPU.com | País de origen: Perú | Año: 2024</p>");
        builder.append("</footer>");
        

        return builder.toString();
    }


    private String generateCSS() {
      
        StringBuilder builder = new StringBuilder();

        builder.append("")

        .append("@page {")
            .append("margin: 0px;")
        .append("}")

        .append("body{")
            .append(" background-color: white;")
            .append("width:300px;")
        .append("}")

        /***********************
         * generateHeaderPdf
         **********************/

         .append("#main-container-header-pdf {")
         .append("background-color: #FF0080;")
         .append("color: white;")
         .append("display:flex;")
     .append("}");

     

     builder.append("#container-datos-header-pdf {")
         .append("width:200%;")
         .append("display:flex;")
         .append("color: white;")
         .append("flex-direction:column;")
         .append("align-items: center;")
         .append("justify-content: space-between;")
     .append("}");

     builder.append("#container-title-header-pdf p {")
         .append("color:#000000;")
         .append("font-size:25px;")
         .append("font-family:Copperplate;")
         .append("text-align: center;")
         .append("margin-bottom: 3px;")
         .append("font-weight: bold;")
     .append("}");

     builder.append("#container-datos-header-pdf p {")
         .append("color:#000000;")
         .append("margin: 0px;")
         .append("text-align: right;")
         .append("margin-right: 30px;")
     .append("}");

     builder.append("#container-datos-header-pdf img {")
         .append("width: 200px;")
         .append("height: 60px;")
         .append("margin-top: 30px;")
         .append("margin-right: 130px;")
     .append("}");

     builder.append("#table {")
         .append("background-color: #FFFF00;")
         .append("font-family: Copperplate;")
         .append("width: 90%;")
         .append("text-align: center;")
         .append("margin-color: #000000;")
         .append("margin: 20% auto;")
         .append("margin-top: 2%;")
         .append("}");

         builder.append("#.tabla-reportes th, .tabla-reportes td {")
         .append("border: 1px solid #000;")
         .append("padding: 8px;")
         
         .append("}");

         builder.append("#.titulo {")
         .append("font-family: 'Roboto', sans-serif;")
         .append("font-size: 24px;")
         
         .append("}");

         builder.append("#.parte-baja {")
         .append("background-color: #Ff5b00;")
         .append("color: white;")
         .append("padding: 10px;")
         .append("text-align: center;")
         .append("width: 100%;")
         .append("position: fixed;")
         .append("bottom: 0;")
         
         .append("}");
         
        return builder.toString();
    }

    
    
    
    
    
}
