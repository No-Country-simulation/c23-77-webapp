import PropTypes from "prop-types";
import "./Confirmacion.css";
import api from "../api/api";

function Confirmacion({ persona, pasoAnterior }) {
  const handleConfirm = async () => {
    try {
      console.log("Enviando datos al backend:", persona);
      await api.post("/personas", persona); // Cambia esta URL según tu backend
      alert("Datos enviados correctamente.");
    } catch (error) {
      console.error("Error al enviar los datos:", error);
      alert("Hubo un problema al enviar los datos.");
    }
  };

  return (
    <div className="Confirmacion">
      <div className="form-container">
        <div className="form-content">
          <h2>Confirmación</h2>
          <p>Revisa tus datos antes de finalizar:</p>
          <ul>
            <li>Nombre: {persona.nombre || "No proporcionado"}</li>
            <li>Apellido Paterno: {persona.apPaterno || "No proporcionado"}</li>
            <li>Apellido Materno: {persona.apMaterno || "No proporcionado"}</li>
            <li>
              Fecha de Nacimiento:{" "}
              {persona.fechaNacimiento || "No proporcionada"}
            </li>
            <li>Sexo: {persona.sexo || "No especificado"}</li>
            <li>
              Lugar de Nacimiento:{" "}
              {persona.lugarNacimiento || "No especificado"}
            </li>
            <li>
              Dirección: {persona.calle || "No especificada"},{" "}
              {persona.municipio || ""}
            </li>
          </ul>
          <button type="button" onClick={pasoAnterior}>
            Regresar
          </button>
          <button type="button" onClick={handleConfirm}>
            Finalizar
          </button>
        </div>
      </div>
    </div>
  );
}

Confirmacion.propTypes = {
  persona: PropTypes.object.isRequired,
  pasoAnterior: PropTypes.func.isRequired,
};

export default Confirmacion;
