import { useNavigate } from "react-router-dom";
import { useForm } from "../hooks/context/formContext";
import "./Confirmacion.css";
import api from "../api/api";
import { useState } from "react";

function Confirmacion({ pasoAnterior }) {
  const { persona } = useForm(); // Obtener persona desde el contexto
  const navigate = useNavigate(); // Hook para redirigir
  const [error, setError] = useState(""); // Estado para almacenar errores

  const handleConfirm = async () => {
    try {
      const personaRequest = {
        nombre: persona.nombre,
        apPaterno: persona.apPaterno,
        apMaterno: persona.apMaterno,
        sexo: persona.sexo,
        lugarNacimiento: persona.lugarNacimiento || null, // Opcional
        fechaNacimiento: persona.fechaNacimiento,
        dni: persona.dni,
        usuarioId: persona.usuarioId || null, // Relación opcional
        direccion: {
          calle: persona.calle,
          municipio: persona.municipio,
          estado: persona.estado,
          pais: persona.pais,
          codigoPostal: persona.codigoPostal,
        },
      };

      console.log("Enviando datos al backend:", personaRequest);
      await api.post("/personas", personaRequest); // Cambia esta URL según tu backend
      alert("Datos enviados correctamente.");

      // Redirige al dashboard después del registro exitoso
      navigate("/dashboard");
    } catch (error) {
      console.error("Error al enviar los datos:", error);

      if (error.response && error.response.data) {
        // Captura el mensaje específico del backend
        const backendMessage =
          error.response.data.message || "Ocurrió un problema.";
        setError(backendMessage);
      } else {
        setError("No se pudo completar el registro. Inténtalo nuevamente.");
      }
    }
  };

  return (
    <div className="Confirmacion">
      <div className="form-container">
        <div className="form-content">
          <h2>Confirmación</h2>
          <p>Revisa tus datos antes de finalizar:</p>
          {error && (
            <div className="error-message">
              <p>{error}</p>
            </div>
          )}
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
            <li>DNI: {persona.dni || "No proporcionado"}</li>
            <li>
              Dirección: {persona.calle || "No especificada"},{" "}
              {persona.municipio || "No especificado"},{" "}
              {persona.estado || "No especificado"},{" "}
              {persona.pais || "No especificado"}
            </li>
            <li>Código Postal: {persona.codigoPostal || "No especificado"}</li>
          </ul>
          <div className="button-group">
            <button type="button" onClick={pasoAnterior}>
              Regresar
            </button>
            <button type="button" onClick={handleConfirm}>
              Finalizar
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Confirmacion;
