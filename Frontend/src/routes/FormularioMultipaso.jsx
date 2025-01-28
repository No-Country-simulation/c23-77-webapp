import { useForm } from "../hooks/context/formContext";
import DatosPersonales from "../components/DatosPersonales";
import DomicilioActual from "../components/DomicilioActual";
import Confirmacion from "../components/Confirmacion";

function FormularioMultipaso() {
  const { paso, setPaso } = useForm();

  const siguientePaso = () => setPaso((prev) => prev + 1);
  const pasoAnterior = () => setPaso((prev) => prev - 1);

  switch (paso) {
    case 1:
      return <DatosPersonales siguientePaso={siguientePaso} />;
    case 2:
      return (
        <DomicilioActual
          siguientePaso={siguientePaso}
          pasoAnterior={pasoAnterior}
        />
      );
    case 3:
      return <Confirmacion pasoAnterior={pasoAnterior} />;
    default:
      return null;
  }
}

export default FormularioMultipaso;
