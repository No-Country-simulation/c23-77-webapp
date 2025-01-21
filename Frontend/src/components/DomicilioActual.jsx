import PropTypes from "prop-types";
import "./DomicilioActual.css";

function DomicilioActual({ persona, setPersona, siguientePaso, pasoAnterior }) {
  const handleChange = (e) => {
    const { id, value } = e.target;
    setPersona({
      ...persona,
      [id]: value,
    });
  };

  return (
    <div className="DomicilioActual">
      <div className="form-container">
        <div className="form-content">
          <h2>Domicilio Actual</h2>
          <form className="col g-3">
            <div className="col-md-10">
              <input
                type="text"
                id="codigoPostal"
                placeholder="Código Postal"
                className="form-control"
                value={persona.codigoPostal}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <input
                type="text"
                id="pais"
                placeholder="País"
                className="form-control mt-2"
                value={persona.pais}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <input
                type="text"
                id="estado"
                placeholder="Estado"
                className="form-control mt-2"
                value={persona.estado}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10">
              <input
                type="text"
                id="municipio"
                placeholder="Municipio"
                className="form-control mt-2"
                value={persona.municipio}
                onChange={handleChange}
                required
              />
            </div>
            <div className="col-md-10 mt-3 d-flex justify-content-between">
              <button
                type="button"
                className="btn btn-secondary"
                onClick={pasoAnterior}
              >
                Regresar
              </button>
              <button
                type="button"
                className="btn btn-primary"
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

DomicilioActual.propTypes = {
  persona: PropTypes.object.isRequired,
  setPersona: PropTypes.func.isRequired,
  siguientePaso: PropTypes.func.isRequired,
  pasoAnterior: PropTypes.func.isRequired,
};

export default DomicilioActual;
