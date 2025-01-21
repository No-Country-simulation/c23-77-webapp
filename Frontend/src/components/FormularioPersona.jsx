import { useState } from "react";
import DatosPersonales from "./DatosPersonales";
import DomicilioActual from "./DomicilioActual";
import Confirmacion from "./Confirmacion";
import "./FormularioPersona.css";

function FormularioPersona() {
  const [persona, setPersona] = useState({
    nombre: "",
    apPaterno: "",
    apMaterno: "",
    sexo: "",
    lugarNacimiento: "",
    fechaNacimiento: "",
    codigoPostal: "",
    pais: "",
    estado: "",
    municipio: "",
    calle: "",
    numeroInt: "",
    numeroExt: "",
  });

  const [paso, setPaso] = useState(1);

  const siguientePaso = () => setPaso((prevPaso) => prevPaso + 1);
  const pasoAnterior = () => setPaso((prevPaso) => prevPaso - 1);

  const finalizar = () => {
    console.log("Datos finales enviados al backend:", persona);
    alert("Datos enviados correctamente. ¡Gracias!");
    // Aquí puedes hacer una llamada al backend si es necesario
    setPaso(1); // Reinicia el flujo tras finalizar
  };

  return (
    <div className="FormularioPersona">
      {paso === 1 && (
        <DatosPersonales
          persona={persona}
          setPersona={setPersona}
          siguientePaso={siguientePaso}
        />
      )}
      {paso === 2 && (
        <DomicilioActual
          persona={persona}
          setPersona={setPersona}
          siguientePaso={siguientePaso}
          pasoAnterior={pasoAnterior}
        />
      )}
      {paso === 3 && (
        <Confirmacion
          persona={persona}
          pasoAnterior={pasoAnterior}
          finalizar={finalizar}
        />
      )}
    </div>
  );
}

export default FormularioPersona;
