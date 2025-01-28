import { useForm } from "../hooks/context/formContext";
import "./DomicilioActual.css";

function DomicilioActual({ siguientePaso, pasoAnterior }) {
  const { persona, setPersona } = useForm();

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
          <form>
            <input
              type="text"
              id="calle"
              placeholder="Calle"
              className="form-control"
              value={persona.calle || ""}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              id="municipio"
              placeholder="Ciudad/Municipio"
              className="form-control mt-2"
              value={persona.municipio || ""}
              onChange={handleChange}
              required
            />
            <input
              type="text"
              id="estado"
              placeholder="Estado/Provincia"
              className="form-control mt-2"
              value={persona.estado || ""}
              onChange={handleChange}
            />
            <input
              type="text"
              id="codigoPostal"
              placeholder="Código Postal"
              className="form-control mt-2"
              value={persona.codigoPostal || ""}
              onChange={handleChange}
            />
            <input
              type="text"
              id="pais"
              placeholder="País"
              className="form-control mt-2"
              value={persona.pais || ""}
              onChange={handleChange}
              required
            />
            <div className="button-group mt-3">
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

export default DomicilioActual;
