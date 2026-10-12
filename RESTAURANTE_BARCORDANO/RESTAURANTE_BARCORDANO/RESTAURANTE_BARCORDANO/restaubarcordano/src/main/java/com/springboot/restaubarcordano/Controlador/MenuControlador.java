package com.springboot.restaubarcordano.Controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.restaubarcordano.Modelo.Entidades.Menu;
import com.springboot.restaubarcordano.Modelo.Service.MenuService;



@Controller
public class MenuControlador {

    @Autowired
    private MenuService menuService;

    //Ruta donde se guardarán las imagenes agregadas a menu sea platos, postres o bebidas
    private static final String CARPETA_IMAGENES = "src/main/resources/static/images/Menu/";

    @GetMapping("/indexmenu")
    public String index(Model modelo) {
        modelo.addAttribute("listaPlatos",  menuService.listarPorCategoria("PLATO"));
        modelo.addAttribute("listaPostres", menuService.listarPorCategoria("POSTRE"));
        modelo.addAttribute("listaBebidas", menuService.listarPorCategoria("BEBIDA"));
        return "indexmenu";
    }

    @GetMapping("/nuevomenu")
    public String nuevo(Model modelo) {
        modelo.addAttribute("menu", new Menu());
        return "viewaddmenu";
    }

    //Ahora MultipartFile recibe la imagen agregada
    @PostMapping("/guardarmenu")
    public String guardar(@ModelAttribute Menu menu,
                          @RequestParam("archivoImagen") MultipartFile archivo) {

        // Si se subió una imagen
        if (!archivo.isEmpty()) {
            try {
                File carpeta = new File(CARPETA_IMAGENES);
                if (!carpeta.exists()) carpeta.mkdirs();

                // Guardar el archivo con su nombre original
                String nombreArchivo = archivo.getOriginalFilename();
                Path ruta = Paths.get(CARPETA_IMAGENES + nombreArchivo);
                Files.write(ruta, archivo.getBytes());

                // Guardar la ruta relativa en la BD
                menu.setImagen("/images/Menu/" + nombreArchivo);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        menuService.guardar(menu);
        return "redirect:/indexmenu";
    }
    
    @GetMapping("/editarmenu/{id}")
    public String editar(@PathVariable int id, Model modelo) {
        Optional<Menu> menuOptional = menuService.buscarId(id);
        if (menuOptional.isPresent()) {
            modelo.addAttribute("menu", menuOptional.get());
            return "viewaddmenu"; 
        } else {
            return "redirect:/indexmenu";
        }
    }

    @GetMapping("/borrarmenu/{id}")
    public String borrar(@PathVariable int id) {
        menuService.borrar(id);
        return "redirect:/indexmenu";
    }
}
