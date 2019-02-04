


module.exports={
  attributes: {
    nombre:{
      type: "string"
      },
    apellido:{
      type: "string"
      },
    fechaNacimiento:{
       type: "string"
      },
    numeroAutos:{
       type:"number"
       },
    licenciaValida:{
          type:"boolean"
       },
    autos:{
             collection:"Auto",
           via:"idConductor"
        }
    },

};
