import { useForm } from "../hooks/context/formContext";
import "./DatosPersonales.css";

function DatosPersonales({ siguientePaso }) {
  const { persona, setPersona } = useForm();

  const handleChange = (e) => {
    const { id, value } = e.target;
    setPersona({
      ...persona,
      [id]: value,
    });
  };

  return (
    <div className="DatosPersonales">
      <div className="form-container">
        <div className="form-content">
          <h2>Datos Personales</h2>
          <p>
            Completa los siguientes datos. Asegúrate de que sean correctos antes
            de continuar.
          </p>
          <form>
            <input
              type="text"
              id="nombre"
              placeholder="Nombre"
              className="form-control"
              value={persona.nombre || ""}
              onChange={handleChange}
              maxLength={100}
              required
            />
            <input
              type="text"
              id="apPaterno"
              placeholder="Apellido Paterno"
              className="form-control mt-2"
              value={persona.apPaterno || ""}
              onChange={handleChange}
              maxLength={100}
              required
            />
            <input
              type="text"
              id="apMaterno"
              placeholder="Apellido Materno"
              className="form-control mt-2"
              value={persona.apMaterno || ""}
              onChange={handleChange}
              maxLength={100}
              required
            />
            <div className="mt-3">
              <label>
                <input
                  type="radio"
                  name="sexo"
                  id="sexo"
                  value="Masculino"
                  checked={persona.sexo === "Masculino"}
                  onChange={handleChange}
                  required
                />
                Masculino
              </label>
              <label className="ms-3">
                <input
                  type="radio"
                  name="sexo"
                  id="sexo"
                  value="Femenino"
                  checked={persona.sexo === "Femenino"}
                  onChange={handleChange}
                  required
                />
                Femenino
              </label>
            </div>
            <input
              type="text"
              id="lugarNacimiento"
              placeholder="Lugar de Nacimiento (opcional)"
              className="form-control mt-2"
              value={persona.lugarNacimiento || ""}
              onChange={handleChange}
              maxLength={100}
            />
            <input
              type="date"
              id="fechaNacimiento"
              className="form-control mt-2"
              value={persona.fechaNacimiento || ""}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              id="dni"
              placeholder="DNI"
              className="form-control mt-2"
              value={persona.dni || ""}
              onChange={handleChange}
              maxLength={20}
              required
            />
            <button
              type="button"
              className="btn btn-primary mt-3"
              onClick={siguientePaso}
            >
              Continuar
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}

export default DatosPersonales;
