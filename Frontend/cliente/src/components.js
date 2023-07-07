import React, { useEffect, useState } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import './component.css';


const TaskList = () => {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8090/api/tasks", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((data) => setTasks(data))
      .catch((error) => console.error(error));
  }, []);

  return (
    <div className="TaskListContainer">
      
      {tasks.map((task) => (
        <div className="TaskListBoxContainer" key={task.id}>
          <h1>Tarea Pendiente: </h1>
          <div className="TaskListBox">
            <p>La tarea es: {task.name}</p>
            <p>Con el id: {task.id}</p>
            <Link to={`/tasks/${task.id}`}>Ver detalles</Link>
          </div>
        </div>
      ))}
    </div>
  );
  
};

const TaskDetails = () => {
  const { taskId } = useParams();
  const [task, setTask] = useState(null);
  const [formData, setFormData] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    fetchTask();
  }, []);

  const fetchTask = async () => {
    try {
      const response = await fetch(`http://localhost:8090/api/tasks/${taskId}`);
      const data = await response.json();
      setTask(data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleValidateTask = async () => {
    try {
      const response = await fetch(
        `http://localhost:8090/api/tasks/${taskId}/validate`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        }
      );
      if (response.ok) {
        alert("La tarea ha sido validada");
        navigate("/"); // Redirige a la página TaskList
      } else {
        const errorData = await response.json();
        console.error("Error al validar la tarea:", errorData);
      }
    } catch (error) {
      console.error(error);
    }
  };

  if (!task) {
    return <div>Cargando...</div>;
  }

  return (
    <div class="taskDone">
      <h1>Detalles de la Tarea</h1>
      <h3>{task.name}</h3>
      <p>ID: {task.id}</p>
      <p>Responsable: {task.formName}</p>
      <p>Mensaje: {task.rejectReason}</p>
      <p>Ancho: {task.formAncho}</p>
      <p>Alto: {task.formAlto}</p>
      <p>Profundo: {task.formProfundo}</p>
      <p>Email: {task.formEmail}</p>
      <p>Madera: {task.formMadera}</p>
      <button onClick={handleValidateTask}>Validar Tarea</button>
    </div>
  );
};

export { TaskList, TaskDetails };
