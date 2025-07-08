async function main() {
  let jsonVideo = await fetch(
    "http://localhost:8080/api/v1/aura/content/c4e551f6-6c10-4ec1-af74-f7963450b051"
  )
    .then((data) => data.json())
    .then((result) => result)
    .catch((error) => console.error(error));

  const container = document.getElementById("video-container");
  container.innerHTML = "";

  // Cria título com classe
  const title = document.createElement("h2");
  title.className = "video-title";
  title.textContent = jsonVideo.contentName;

  // Cria descrição com classe
  const desc = document.createElement("p");
  desc.className = "video-description";
  desc.textContent = jsonVideo.contentDescription;

  // Cria video wrapper com classe
  const videoWrapper = document.createElement("div");
  videoWrapper.className = "video-wrapper";

  // Cria o elemento <video> com classe
  const video = document.createElement("video");
  video.setAttribute("controls", "");
  video.className = "video-player";

  // Cria o <source> e define o src base64
  const source = document.createElement("source");
  source.type = "video/mp4";
  source.src = `data:video/mp4;base64,${jsonVideo.contentFileBytes}`;

  video.appendChild(source);
  videoWrapper.appendChild(video);

  // Adiciona tudo no container
  container.appendChild(title);
  container.appendChild(videoWrapper);
  container.appendChild(desc);
}

main();
