import "./Registro.css"; // Similar a Login.css
import registroImg from "../assets/login-img.png"; // Cambia la imagen según necesites
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useForm } from "../hooks/context/formContext"; // Importar el contexto
import api from "../api/api";

function Registro() {
  const [usuario, setUsuario] = useState({
    email: "",
    contrasena: "",
    telefono: "",
    rol: "PARTICULAR", // Rol por defecto
  });
  const [error, setError] = useState("");
  const { setPersona } = useForm(); // Obtener el setter del contexto
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { id, value } = e.target;
    setUsuario({
      ...usuario,
      [id]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await api.post("/usuarios/registro", usuario);
      const usuarioId = response.data.id;

      if (usuario.rol === "PARTICULAR") {
        // Actualiza el contexto global con el usuarioId
        await new Promise((resolve) => {
          setPersona((prevPersona) => {
            resolve();
            return {
              ...prevPersona,
              usuarioId: usuarioId, // Transfiere el ID al formulario
            };
          });
        });

        // Redirige al FormularioMultipaso
        navigate("/formulario");
      } else {
        // Si es Admin o Empleado, redirige al Dashboard
        navigate("/dashboard");
      }
    } catch (error) {
      if (error.response && error.response.data) {
        const { errors } = error.response.data;

        if (errors) {
          const formattedErrors = Object.entries(errors)
            .map(([field, message]) => `${field}: ${message}`)
            .join(". ");
          setError(formattedErrors);
        } else {
          setError(
            error.response.data.message || "Ocurrió un error inesperado."
          );
        }
      } else {
        setError("No se pudo completar el registro. Inténtalo nuevamente.");
      }
    }
  };

  return (
    <div className="Registro">
      <div className="form-container">
        <div className="form-content">
          <div className="text-container">
            <h2>Bienvenido a</h2>
            <h1>Tu Banquito</h1>
          </div>
          <form className="col g-3" onSubmit={handleSubmit}>
            <div className="col-md-10">
              <label htmlFor="email" className="form-label fw-bold">
                Correo Electrónico
              </label>
              <input
                type="email"
                className="form-control"
                id="email"
                placeholder="Ingrese su correo electrónico"
                value={usuario.email}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <label htmlFor="contrasena" className="form-label mt-2 fw-bold">
                Contraseña
              </label>
              <input
                type="password"
                className="form-control"
                id="contrasena"
                placeholder="Cree una contraseña"
                value={usuario.contrasena}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <label htmlFor="telefono" className="form-label mt-2 fw-bold">
                Teléfono
              </label>
              <input
                type="text"
                className="form-control"
                id="telefono"
                placeholder="Ingrese su número de teléfono"
                value={usuario.telefono}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <label htmlFor="rol" className="form-label mt-2 fw-bold">
                Rol
              </label>
              <select
                id="rol"
                className="form-control"
                value={usuario.rol}
                onChange={handleChange}
                required
              >
                <option value="PARTICULAR">Cliente</option>
                <option value="ADMIN">Admin</option>
                <option value="EMPLEADO">Empleado</option>
              </select>
            </div>
            {error && (
              <div className="col-md-10 text-danger mt-2">
                <small>{error}</small>
              </div>
            )}
            <div className="col-12">
              <button type="submit" className="btn btn-primary mt-3 mb-3">
                Registrar
              </button>
            </div>
          </form>
        </div>
        <div className="switch-container">
          <p>¿Ya tienes una cuenta?</p>
          <button
            className="btn btn-secondary"
            onClick={() => navigate("/login")}
          >
            Iniciar sesión
          </button>
        </div>

        <div className="img-registro">
          <img src={registroImg} alt="Registro" />
        </div>
      </div>
    </div>
  );
}

export default Registro;
