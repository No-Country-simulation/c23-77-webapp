import PropTypes from "prop-types";
import "./DatosPersonales.css";

function DatosPersonales({ persona, setPersona, siguientePaso }) {
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
          <div className="text-container">
            <h2>Datos Personales</h2>
            <p>
              Para iniciar el contrato registra los datos y asegúrate que sean
              correctos.
            </p>
          </div>
          <form className="col g-3">
            <div className="col-md-10">
              <input
                type="text"
                id="nombre"
                placeholder="Nombre"
                className="form-control"
                value={persona.nombre}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <input
                type="text"
                id="apPaterno"
                placeholder="Apellido Paterno"
                className="form-control mt-2"
                value={persona.apPaterno}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <input
                type="text"
                id="apMaterno"
                placeholder="Apellido Materno"
                className="form-control mt-2"
                value={persona.apMaterno}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <input
                type="date"
                id="fechaNacimiento"
                placeholder="Fecha de Nacimiento"
                className="form-control mt-2"
                value={persona.fechaNacimiento}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <input
                type="text"
                id="lugarNacimiento"
                placeholder="Lugar de Nacimiento"
                className="form-control mt-2"
                value={persona.lugarNacimiento}
                onChange={handleChange}
              />
            </div>
            <div className="col-md-10 mt-3">
              <label className="me-3">
                <input
                  type="radio"
                  name="sexo"
                  id="sexo"
                  value="Masculino"
                  onChange={handleChange}
                  required
                />
                Masculino
              </label>
              <label>
                <input
                  type="radio"
                  name="sexo"
                  id="sexo"
                  value="Femenino"
                  onChange={handleChange}
                  required
                />
                Femenino
              </label>
            </div>
            <div className="col-12">
              <button
                type="button"
                className="btn btn-primary mt-3"
                onClick={siguientePaso}
              >
                Continuar
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

DatosPersonales.propTypes = {
  persona: PropTypes.object.isRequired,
  setPersona: PropTypes.func.isRequired,
  siguientePaso: PropTypes.func.isRequired,
};

export default DatosPersonales;
