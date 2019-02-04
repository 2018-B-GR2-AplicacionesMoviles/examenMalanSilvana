module.exports = {
    attributes: {
        chasis: {
            type: "number"
        },
        nombreMarca: {
            type: "string"
        },
        colorUno: {
            type: "string"
        },
        colorDos: {
            type: "string"
        },
        nombreModelo: {
            type: "string"
        },
        anio: {
            type: "number"
        },
        idConductor: {
            model:"Conductor"
        }
    },
};
