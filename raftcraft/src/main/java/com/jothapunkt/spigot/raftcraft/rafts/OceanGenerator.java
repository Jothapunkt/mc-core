package com.jothapunkt.spigot.raftcraft.rafts;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class OceanGenerator extends ChunkGenerator {
    public static int SEA_LEVEL = 64;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.003D);

        for (int X = 0; X < 16; X++)
            for (int Z = 0; Z < 16; Z++) {
                int highestBlockLevel = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*5D+10D);

                // Random value 0-1000 based on noise, used to determine block type
                int blockTypeValue = (int) (generator.noise(chunkZ*16+Z+1000, chunkX*16+X+1000, 2.5D, 0.5D)*500D+500D);

                // Bedrock
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
                
                // Block Layers
                for (int Y = 1; Y <= highestBlockLevel; Y++) {
                    if (blockTypeValue < 100) {
                        chunk.setBlock(X, Y, Z, Material.SAND);
                    } else if (blockTypeValue > 400 && blockTypeValue <= 500) {
                        chunk.setBlock(X, Y, Z, Material.DIRT);
                    } else if (blockTypeValue > 950) {
                        chunk.setBlock(X, Y, Z, Material.CLAY);
                    } else {
                        chunk.setBlock(X, Y, Z, Material.GRAVEL);
                    }
                }

                // Fill up with water
                for (int Y = highestBlockLevel + 1; Y <= SEA_LEVEL; Y++) {
                    chunk.setBlock(X, Y, Z, Material.WATER);
                }

                // 10% of blocks should spawn with seagrass
                if (random.nextInt(1000) <= 100) {
                    chunk.setBlock(X, highestBlockLevel + 1, Z, Material.SEAGRASS);
                }

                // 3% of blocks should spawn with tall seagrass
                if (random.nextInt(1000) <= 30) {
                    chunk.setBlock(X, highestBlockLevel + 1, Z, Material.TALL_SEAGRASS);
                }

                // 0.05% of blocks should spawn with Kelp
                if (random.nextInt(10000) <= 5) {
                    chunk.setBlock(X, highestBlockLevel + 1, Z, Material.KELP);
                }

            }
        return chunk;
    }
}
