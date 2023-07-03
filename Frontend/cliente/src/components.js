import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

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
    <div>
      <h1>Tareas Pendientes</h1>
      {tasks.map((task) => (
        <div key={task.id}>
          <h3>{task.name}</h3>
          <h3>{task.id}</h3>
          <p>Assignee: {task.assignee}</p>
          <Link to={`/tasks/${task.id}`}>Ver detalles</Link>
        </div>
      ))}
    </div>
  );
};

const TaskDetails = () => {
  const { taskId } = useParams();
  const [task, setTask] = useState(null);

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
      await fetch(`http://localhost:8090/api/tasks/${taskId}/validate`, {
        method: "POST",
      });
      if (task.valid === true) {
        alert("La tarea ha sido validada");
      }
    } catch (error) {
      console.error(error);
    }
  };

  if (!task) {
    return <div>Cargando...</div>;
  }

  return (
    <div>
      <h1>Detalles de la Tarea</h1>
      <h3>{task.name}</h3>
      <p>ID: {task.id}</p>
      <p>Responsable: {task.assignee}</p>
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
